package com.mercadona.library.loans.driven.clients.adapter;

import com.mercadona.library.loans.application.ports.driven.BookClientPort;
import com.mercadona.library.loans.application.utils.pagination.PagedResult;
import com.mercadona.library.loans.domain.BookCatalogEntry;
import com.mercadona.library.loans.driven.clients.client.BooksApiWebClient;
import com.mercadona.library.loans.driven.clients.mapper.BookRestClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BooksRestClientAdapter implements BookClientPort {

  private final BooksApiWebClient booksApiWebClient;
  private final BookRestClientMapper mapper;

  @Override
  public PagedResult<BookCatalogEntry> findAll(Integer firstPage, Integer pageSize, String sort) {
    var response = booksApiWebClient.getBooks(firstPage, pageSize, sort);
    return mapper.toDomain(response);
  }
}
