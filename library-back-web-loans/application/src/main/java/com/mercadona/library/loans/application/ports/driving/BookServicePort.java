package com.mercadona.library.loans.application.ports.driving;

import com.mercadona.library.loans.application.utils.pagination.PagedResult;
import com.mercadona.library.loans.domain.BookCatalogEntry;

public interface BookServicePort {
  PagedResult<BookCatalogEntry> getBookCollection(Integer firstPage, Integer pageSize, String sort);
}
