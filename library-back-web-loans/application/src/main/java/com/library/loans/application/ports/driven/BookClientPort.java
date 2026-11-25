package com.library.loans.application.ports.driven;

import com.library.loans.application.utils.pagination.PagedResult;
import com.library.loans.domain.BookCatalogEntry;

public interface BookClientPort {
  PagedResult<BookCatalogEntry> findAll(Integer firstPage, Integer pageSize, String sort);
}
