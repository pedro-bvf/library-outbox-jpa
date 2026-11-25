package com.mercadona.library.books.driving.controllers.adapters;

import com.mercadona.library.api.web_books.definition.server.BooksApi;
import com.mercadona.library.api.web_books.model.*;
import com.mercadona.library.books.application.ports.driving.BookServicePort;
import com.mercadona.library.books.driving.controllers.mappers.BookControllerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BookControllerAdapter implements BooksApi {
  private final BookServicePort bookServicePort;
  private final BookControllerMapper bookControllerMapper;

  @Override
  public ResponseEntity<BookResourceCollectionResponse> getBookResourceCollection(
    Integer firstPage,
    Integer pageSize,
    String sort
  ) {
    var result = bookServicePort.getAllBooks(firstPage, pageSize, sort);
    return ResponseEntity.ok(bookControllerMapper.toBookResourceCollectionResponse(result));
  }

  @Override
  public ResponseEntity<BookResourceResponse> getBookById(UUID id) {
    var book = bookServicePort.getBook(id);
    return ResponseEntity.ok(bookControllerMapper.toBookResourceResponse(book));
  }

  @Override
  public ResponseEntity<BookResourceResponse> createBook(BookResourceRequest bookResourceRequest) {
    var book = bookControllerMapper.toBookDTO(bookResourceRequest);
    var created = bookServicePort.saveBook(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(bookControllerMapper.toBookResourceResponse(created));
  }

  @Override
  public ResponseEntity<BookResourceResponse> updateBookById(UUID id, BookResourceRequest bookResourceRequest) {
    var changes = bookControllerMapper.toBookDTO(bookResourceRequest);
    var updated = bookServicePort.updateBook(id, changes);
    return ResponseEntity.ok(bookControllerMapper.toBookResourceResponse(updated));
  }

  @Override
  public ResponseEntity<Void> deleteBookById(UUID id) {
    bookServicePort.deleteBook(id);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<BookResourceCollection> searchBooksByPublicIds(SearchBooksByPublicIdsRequest searchBooksByPublicIdsRequest) {
    var books = bookServicePort.searchBooksByPublicIds(searchBooksByPublicIdsRequest.getPublicIds());
    return ResponseEntity.ok(bookControllerMapper.toBookResourceCollection(books));
  }
}
