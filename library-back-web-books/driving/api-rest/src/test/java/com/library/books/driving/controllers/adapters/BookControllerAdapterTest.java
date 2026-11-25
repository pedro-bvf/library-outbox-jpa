package com.library.books.driving.controllers.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.library.api.web_books.model.BookResourceCollection;
import com.library.api.web_books.model.BookResourceCollectionResponse;
import com.library.api.web_books.model.BookResourceRequest;
import com.library.api.web_books.model.BookResourceResponse;
import com.library.api.web_books.model.SearchBooksByPublicIdsRequest;
import com.library.books.application.ports.driving.BookServicePort;
import com.library.books.domain.BookDTO;
import com.library.books.application.utils.pagination.PagedResult;
import com.library.books.driving.controllers.mappers.BookControllerMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class BookControllerAdapterTest {

  @Mock private BookServicePort bookServicePort;

  @Mock private BookControllerMapper bookControllerMapper;

  @InjectMocks private BookControllerAdapter bookControllerAdapter;

  private UUID bookId;
  private BookDTO book;

  @BeforeEach
  void setUp() {
    bookId = UUID.randomUUID();
    book = new BookDTO(bookId, 1L, "Title", null, null, null, null, null, null, null, null, null);
  }

  @Test
  void getBookResourceCollection_delegatesToUseCaseAndMapsResult() {
    var pagedResult = new PagedResult<>(List.of(book), null);
    var response = new BookResourceCollectionResponse();
    when(bookServicePort.getAllBooks(1, 10, "+title")).thenReturn(pagedResult);
    when(bookControllerMapper.toBookResourceCollectionResponse(pagedResult)).thenReturn(response);

    var result = bookControllerAdapter.getBookResourceCollection(1, 10, "+title");

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result.getBody()).isSameAs(response);
  }

  @Test
  void getBookById_returnsMappedBook() {
    var response = new BookResourceResponse();
    when(bookServicePort.getBook(bookId)).thenReturn(book);
    when(bookControllerMapper.toBookResourceResponse(book)).thenReturn(response);

    var result = bookControllerAdapter.getBookById(bookId);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result.getBody()).isSameAs(response);
  }

  @Test
  void createBook_mapsRequestCreatesAndReturnsCreatedStatus() {
    var request = new BookResourceRequest().title("New Title");
    var toCreate = new BookDTO(
      null, null, "New Title", null, null, null, null, null, null, null, null, null);
    var response = new BookResourceResponse();
    when(bookControllerMapper.toBookDTO(request)).thenReturn(toCreate);
    when(bookServicePort.saveBook(toCreate)).thenReturn(book);
    when(bookControllerMapper.toBookResourceResponse(book)).thenReturn(response);

    var result = bookControllerAdapter.createBook(request);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(result.getBody()).isSameAs(response);
  }

  @Test
  void updateBookById_mapsRequestUpdatesAndMapsResponse() {
    var request = new BookResourceRequest().title("New Title");
    var changes = new BookDTO(
      null, null, "New Title", null, null, null, null, null, null, null, null, null);
    var updated = new BookDTO(
      bookId, 1L, "New Title", null, null, null, null, null, null, null, null, null);
    var response = new BookResourceResponse();
    when(bookControllerMapper.toBookDTO(request)).thenReturn(changes);
    when(bookServicePort.updateBook(bookId, changes)).thenReturn(updated);
    when(bookControllerMapper.toBookResourceResponse(updated)).thenReturn(response);

    var result = bookControllerAdapter.updateBookById(bookId, request);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result.getBody()).isSameAs(response);
  }

  @Test
  void deleteBookById_deletesAndReturnsNoContent() {
    var result = bookControllerAdapter.deleteBookById(bookId);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    assertThat(result.getBody()).isNull();
    verify(bookServicePort).deleteBook(bookId);
  }

  @Test
  void searchBooksByPublicIds_delegatesToUseCaseAndMapsResult() {
    var request = new SearchBooksByPublicIdsRequest().publicIds(List.of(1L, 2L));
    var books = List.of(book);
    var collection = new BookResourceCollection();
    when(bookServicePort.searchBooksByPublicIds(List.of(1L, 2L))).thenReturn(books);
    when(bookControllerMapper.toBookResourceCollection(books)).thenReturn(collection);

    var result = bookControllerAdapter.searchBooksByPublicIds(request);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result.getBody()).isSameAs(collection);
  }
}
