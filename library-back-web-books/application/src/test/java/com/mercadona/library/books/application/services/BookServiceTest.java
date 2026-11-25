package com.mercadona.library.books.application.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mercadona.library.books.application.exceptions.BadRequestException;
import com.mercadona.library.books.application.exceptions.ConflictException;
import com.mercadona.library.books.application.exceptions.NotFoundException;
import com.mercadona.library.books.application.ports.driven.AuthorRepositoryPort;
import com.mercadona.library.books.application.ports.driven.BookOutboxPublisherPort;
import com.mercadona.library.books.application.ports.driven.BookRepositoryPort;
import com.mercadona.library.books.application.ports.driven.PublisherRepositoryPort;
import com.mercadona.library.books.domain.AuthorDTO;
import com.mercadona.library.books.domain.BookDTO;
import com.mercadona.library.books.domain.PublisherDTO;
import com.mercadona.library.books.application.utils.pagination.PagedResult;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

  @Mock private BookRepositoryPort bookRepository;
  @Mock private BookOutboxPublisherPort bookOutboxPublisher;
  @Mock private PublisherRepositoryPort publisherRepositoryPort;
  @Mock private AuthorRepositoryPort authorRepositoryPort;

  @InjectMocks private BookService bookService;

  private UUID bookId;
  private BookDTO book;

  @BeforeEach
  void setUp() {
    bookId = UUID.randomUUID();
    book = bookDTO(bookId);
  }

  @Test
  void getAllBooks_delegatesToRepositoryAndReturnsItsResult() {
    var pagedResult = new PagedResult<>(List.of(book), null);
    when(bookRepository.findAllBooks(0, 10, "+title")).thenReturn(pagedResult);

    var result = bookService.getAllBooks(0, 10, "+title");

    assertThat(result).isSameAs(pagedResult);
  }

  @Test
  void getBook_returnsBookWhenItExists() {
    when(bookRepository.findBookById(bookId)).thenReturn(Optional.of(book));

    var result = bookService.getBook(bookId);

    assertThat(result).isEqualTo(book);
  }

  @Test
  void getBook_throwsNotFoundExceptionWhenBookDoesNotExist() {
    when(bookRepository.findBookById(bookId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> bookService.getBook(bookId))
      .isInstanceOf(NotFoundException.class)
      .hasMessage("Book with id " + bookId + " not found.");
  }

  @Test
  void createBook_delegatesToRepositoryAndReturnsItsResult() {
    var publisherId = UUID.randomUUID();
    var publisher = new PublisherDTO(publisherId, null, null, null);
    var toCreate = new BookDTO(
      null, 1L, "Title", null, null, null, null, null, null, null, publisher, null);
    when(publisherRepositoryPort.findById(publisherId)).thenReturn(Optional.of(publisher));
    when(bookRepository.createBook(any(BookDTO.class))).thenReturn(book);

    var result = bookService.saveBook(toCreate);

    assertThat(result).isEqualTo(book);
    verify(bookOutboxPublisher).saveBookOutbox(book);
  }

  @Test
  void createBook_passesResolvedPublisherAndAuthorsToRepository() {
    var publisherId = UUID.randomUUID();
    var authorId = UUID.randomUUID();
    var toCreate = new BookDTO(
      null, 1L, "Title", null, null, null, null, null, null, null,
      new PublisherDTO(publisherId, null, null, null),
      Set.of(new AuthorDTO(authorId, null, null, null, null)));
    var resolvedPublisher = new PublisherDTO(publisherId, 10L, "Publisher Name", "Address");
    var resolvedAuthor = new AuthorDTO(authorId, 20L, "Author Name", null, null);
    when(publisherRepositoryPort.findById(publisherId)).thenReturn(Optional.of(resolvedPublisher));
    when(authorRepositoryPort.findAllById(List.of(authorId))).thenReturn(List.of(resolvedAuthor));
    var bookCaptor = ArgumentCaptor.forClass(BookDTO.class);
    when(bookRepository.createBook(bookCaptor.capture())).thenReturn(book);

    bookService.saveBook(toCreate);

    assertThat(bookCaptor.getValue().publisher()).isEqualTo(resolvedPublisher);
    assertThat(bookCaptor.getValue().authors()).containsExactly(resolvedAuthor);
  }

  @Test
  void createBook_throwsInvalidBookExceptionWhenPublisherIsMissing() {
    var toCreate = new BookDTO(
      null, 1L, "Title", null, null, null, null, null, null, null, null, null);

    assertThatThrownBy(() -> bookService.saveBook(toCreate))
      .isInstanceOf(BadRequestException.class)
      .hasMessage("Publisher is required to create a book.");

    verify(bookRepository, never()).createBook(any());
  }

  @Test
  void createBook_throwsBadRequestExceptionWhenPublicIdIsMissing() {
    var publisherId = UUID.randomUUID();
    var toCreate = new BookDTO(
      null, null, "Title", null, null, null, null, null, null, null,
      new PublisherDTO(publisherId, null, null, null), null);

    assertThatThrownBy(() -> bookService.saveBook(toCreate))
      .isInstanceOf(BadRequestException.class)
      .hasMessage("PublicId is required to create a book.");

    verify(publisherRepositoryPort, never()).findById(any());
    verify(bookRepository, never()).createBook(any());
  }

  @Test
  void createBook_throwsConflictExceptionWhenPublicIdAlreadyExists() {
    var publisherId = UUID.randomUUID();
    var toCreate = new BookDTO(
      null, 1L, "Title", null, null, null, null, null, null, null,
      new PublisherDTO(publisherId, null, null, null), null);
    when(bookRepository.findByPublicIds(List.of(1L))).thenReturn(List.of(book));

    assertThatThrownBy(() -> bookService.saveBook(toCreate))
      .isInstanceOf(ConflictException.class)
      .hasMessage("A book with publicId 1 already exists.");

    verify(publisherRepositoryPort, never()).findById(any());
    verify(bookRepository, never()).createBook(any());
  }

  @Test
  void createBook_throwsNotFoundExceptionWhenPublisherDoesNotExist() {
    var publisherId = UUID.randomUUID();
    var toCreate = new BookDTO(
      null, 1L, "Title", null, null, null, null, null, null, null,
      new PublisherDTO(publisherId, null, null, null), null);
    when(publisherRepositoryPort.findById(publisherId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> bookService.saveBook(toCreate))
      .isInstanceOf(NotFoundException.class)
      .hasMessage("Publisher with id " + publisherId + " not found.");

    verify(bookRepository, never()).createBook(any());
  }

  @Test
  void createBook_throwsNotFoundExceptionWhenAnyAuthorDoesNotExist() {
    var publisherId = UUID.randomUUID();
    var authorId = UUID.randomUUID();
    var toCreate = new BookDTO(
      null, 1L, "Title", null, null, null, null, null, null, null,
      new PublisherDTO(publisherId, null, null, null),
      Set.of(new AuthorDTO(authorId, null, null, null, null)));
    when(publisherRepositoryPort.findById(publisherId)).thenReturn(Optional.of(new PublisherDTO(publisherId, null, null, null)));
    when(authorRepositoryPort.findAllById(List.of(authorId))).thenReturn(List.of());

    assertThatThrownBy(() -> bookService.saveBook(toCreate))
      .isInstanceOf(NotFoundException.class);

    verify(bookRepository, never()).createBook(any());
  }

  @Test
  void updateBook_returnsUpdatedBookWhenItExists() {
    var changes = bookDTO(bookId);
    when(bookRepository.updateBook(bookId, changes)).thenReturn(Optional.of(book));

    var result = bookService.updateBook(bookId, changes);

    assertThat(result).isEqualTo(book);
  }

  @Test
  void updateBook_throwsNotFoundExceptionWhenBookDoesNotExist() {
    var changes = bookDTO(bookId);
    when(bookRepository.updateBook(bookId, changes)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> bookService.updateBook(bookId, changes))
      .isInstanceOf(NotFoundException.class)
      .hasMessage("Book with id " + bookId + " not found.");
  }

  @Test
  void deleteBook_deletesBookWhenItExists() {
    when(bookRepository.findBookById(bookId)).thenReturn(Optional.of(book));

    bookService.deleteBook(bookId);

    verify(bookRepository).deleteBookById(bookId);
    verify(bookOutboxPublisher).deleteBookOutbox(book);
  }

  @Test
  void deleteBook_throwsNotFoundExceptionAndNeverDeletesWhenBookDoesNotExist() {
    when(bookRepository.findBookById(bookId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> bookService.deleteBook(bookId))
      .isInstanceOf(NotFoundException.class)
      .hasMessage("Book with id " + bookId + " not found.");

    verify(bookRepository, never()).deleteBookById(any());
  }

  @Test
  void searchBooksByPublicIds_delegatesToRepositoryAndReturnsItsResult() {
    var publicIds = List.of(1L, 2L);
    var books = List.of(book);
    when(bookRepository.findByPublicIds(publicIds)).thenReturn(books);

    var result = bookService.searchBooksByPublicIds(publicIds);

    assertThat(result).isSameAs(books);
  }

  private BookDTO bookDTO(UUID id) {
    return new BookDTO(id, 1L, "Title", null, null, null, null, null, null, null, null, null);
  }
}
