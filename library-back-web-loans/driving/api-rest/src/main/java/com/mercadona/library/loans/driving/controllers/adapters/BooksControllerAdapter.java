package com.mercadona.library.loans.driving.controllers.adapters;

import com.mercadona.library.api.web_loans.definition.server.BooksApi;
import com.mercadona.library.api.web_loans.model.BookCollectionResponse;
import com.mercadona.library.loans.application.ports.driving.BookServicePort;
import com.mercadona.library.loans.driving.controllers.mappers.BooksDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BooksControllerAdapter implements BooksApi {
  private final BookServicePort bookService;
  private final BooksDTOMapper booksDTOMapper;

  @Override
  public ResponseEntity<BookCollectionResponse> getBookCollection(
      Integer firstPage, Integer pageSize, String sort
  ) {
    var books = bookService.getBookCollection(firstPage, pageSize, sort);
    return ResponseEntity.ok(booksDTOMapper.toBookCollectionResponse(books));
  }
}
