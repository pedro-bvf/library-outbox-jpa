package com.mercadona.library.loans.application.services;

import com.mercadona.library.loans.application.ports.driven.BookClientPort;
import com.mercadona.library.loans.application.ports.driving.BookServicePort;
import com.mercadona.library.loans.application.utils.pagination.PagedResult;
import com.mercadona.library.loans.domain.BookCatalogEntry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceUseCase implements BookServicePort {

  private final BookClientPort bookClientPort;

  @Override
  public PagedResult<BookCatalogEntry> getBookCollection(Integer firstPage, Integer pageSize, String sort) {
    return bookClientPort.findAll(firstPage, pageSize, sort);
  }
}
