package com.mercadona.library.books.application.services;

import com.mercadona.library.books.application.exceptions.BadRequestException;
import com.mercadona.library.books.application.exceptions.ConflictException;
import com.mercadona.library.books.application.exceptions.NotFoundException;
import com.mercadona.library.books.application.ports.driven.AuthorRepositoryPort;
import com.mercadona.library.books.application.ports.driven.BookOutboxPublisherPort;
import com.mercadona.library.books.application.ports.driven.BookRepositoryPort;
import com.mercadona.library.books.application.ports.driven.PublisherRepositoryPort;
import com.mercadona.library.books.application.ports.driving.BookServicePort;
import com.mercadona.library.books.domain.AuthorDTO;
import com.mercadona.library.books.domain.BookDTO;
import com.mercadona.library.books.domain.PublisherDTO;
import com.mercadona.library.books.application.utils.pagination.PagedResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService implements BookServicePort {
  private final BookRepositoryPort bookRepository;
  private final BookOutboxPublisherPort bookOutboxPublisher;
  private final PublisherRepositoryPort publisherRepositoryPort;
  private final AuthorRepositoryPort authorRepositoryPort;

  @Override
  @Transactional(readOnly = true)
  public PagedResult<BookDTO> getAllBooks(int pageNumber, int pageSize, String sort) {
    return bookRepository.findAllBooks(pageNumber, pageSize, sort);
  }

  @Override
  @Transactional(readOnly = true)
  public BookDTO getBook(UUID id) {
    return bookRepository.findBookById(id)
      .orElseThrow(() -> NotFoundException.of("Book with id %s not found.", id));
  }

  @Override
  @Transactional
  public BookDTO saveBook(BookDTO book) {
    if (book.publicId() == null) {
      throw BadRequestException.of("PublicId is required to create a book.");
    }
    if (!bookRepository.findByPublicIds(List.of(book.publicId())).isEmpty()) {
      throw ConflictException.of("A book with publicId %s already exists.", book.publicId());
    }

    var publisher = resolvePublisher(book.publisher());
    var authors = resolveAuthors(book.authors());
    var bookToCreate = book.withResolvedPublisherAndAuthors(publisher, authors);

    var createdBook = bookRepository.createBook(bookToCreate);
    bookOutboxPublisher.saveBookOutbox(createdBook);
    return createdBook;
  }

  @Override
  @Transactional
  public BookDTO updateBook(UUID id, BookDTO book) {
    var updatedBook = bookRepository.updateBook(id, book)
      .orElseThrow(() -> NotFoundException.of("Book with id %s not found.", id));

    bookOutboxPublisher.saveBookOutbox(updatedBook);
    return updatedBook;
  }

  @Override
  @Transactional
  public void deleteBook(UUID id) {
    var bookToDelete = bookRepository.findBookById(id)
      .orElseThrow(() -> NotFoundException.of("Book with id %s not found.", id));

    bookRepository.deleteBookById(bookToDelete.id());
    bookOutboxPublisher.deleteBookOutbox(bookToDelete);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookDTO> searchBooksByPublicIds(List<Long> publicIds) {
    return bookRepository.findByPublicIds(publicIds);
  }

  private PublisherDTO resolvePublisher(PublisherDTO publisher) {
    if (publisher == null || publisher.id() == null) {
      throw BadRequestException.of("Publisher is required to create a book.");
    }

    return publisherRepositoryPort.findById(publisher.id())
      .orElseThrow(() -> NotFoundException.of("Publisher with id %s not found.", publisher.id()));
  }

  private Set<AuthorDTO> resolveAuthors(Set<AuthorDTO> authors) {
    if (CollectionUtils.isEmpty(authors)) return Set.of();

    var authorIds = authors.stream().map(AuthorDTO::id).toList();
    var found = authorRepositoryPort.findAllById(authorIds);
    if (found.size() != authorIds.size()) {
      throw NotFoundException.of("One or more authors not found for ids %s.", authorIds);
    }
    return new HashSet<>(found);
  }
}
