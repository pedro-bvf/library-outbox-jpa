package com.library.books.driven.repositories.adapters;

import com.library.books.application.ports.driven.BookRepositoryPort;
import com.library.books.domain.AuthorDTO;
import com.library.books.domain.BookDTO;
import com.library.books.domain.PublisherDTO;
import com.library.books.application.utils.pagination.PagedResult;
import com.library.books.application.utils.pagination.PageableBuilder;
import com.library.books.driven.repositories.mappers.AuthorRepositoryMapper;
import com.library.books.driven.repositories.mappers.BookRepositoryMapper;
import com.library.books.driven.repositories.mappers.PublisherRepositoryMapper;
import com.library.books.driven.repositories.models.AuthorMO;
import com.library.books.driven.repositories.models.PublisherMO;
import com.library.books.driven.repositories.repository.BookMOJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookRepositoryAdapter implements BookRepositoryPort {
  private final BookMOJpaRepository bookRepository;
  private final BookRepositoryMapper bookMapper;
  private final PublisherRepositoryMapper publisherMapper;
  private final AuthorRepositoryMapper authorMapper;

  @Override
  public PagedResult<BookDTO> findAllBooks(int pageNumber, int pageSize, String sort) {
    var pageable = PageableBuilder.toPageable(pageNumber, pageSize, sort);
    var bookPage = bookRepository.findAll(pageable).map(bookMapper::toBookDTO);

    return PagedResult.from(bookPage);
  }

  @Override
  public Optional<BookDTO> findBookById(UUID id) {
    return bookRepository.findById(id).map(bookMapper::toBookDTO);
  }

  @Override
  public BookDTO createBook(BookDTO book) {
    var bookMO = bookMapper.toBookMO(book);
    bookMO.setPublisher(toPublisherReference(book.publisher()));
    bookMO.setAuthors(toAuthorReferences(book.authors()));

    return bookMapper.toBookDTO(bookRepository.save(bookMO));
  }

  @Override
  public Optional<BookDTO> updateBook(UUID id, BookDTO changes) {
    return bookRepository.findById(id).map(existing -> {
        bookMapper.updateBookMOFromDTO(changes, existing);
        return existing;
      })
      .map(bookRepository::save)
      .map(bookMapper::toBookDTO);
  }

  @Override
  public void deleteBookById(UUID id) {
    bookRepository.deleteById(id);
  }

  @Override
  public List<BookDTO> findByPublicIds(List<Long> publicIds) {
    if (CollectionUtils.isEmpty(publicIds)) return List.of();

    return bookRepository.findByPublicIdIn(publicIds).stream().map(bookMapper::toBookDTO).toList();
  }

  private PublisherMO toPublisherReference(PublisherDTO publisher) {
    if (publisher == null || publisher.id() == null) return null;

    return publisherMapper.toPublisherMO(publisher);
  }

  private Set<AuthorMO> toAuthorReferences(Set<AuthorDTO> authors) {
    if (CollectionUtils.isEmpty(authors)) return Set.of();

    return authors.stream().map(authorMapper::toAuthorMO).collect(Collectors.toSet());
  }
}
