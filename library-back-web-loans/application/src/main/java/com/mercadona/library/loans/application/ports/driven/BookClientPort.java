package com.mercadona.library.loans.application.ports.driven;

import com.mercadona.library.loans.application.utils.pagination.PagedResult;
import com.mercadona.library.loans.domain.BookCatalogEntry;

public interface BookClientPort {
  PagedResult<BookCatalogEntry> findAll(Integer firstPage, Integer pageSize, String sort);
}
