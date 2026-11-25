package com.library.loans.application.ports.driving;

import com.library.loans.application.utils.pagination.PagedResult;
import com.library.loans.domain.BookCatalogEntry;

public interface BookServicePort {
  PagedResult<BookCatalogEntry> getBookCollection(Integer firstPage, Integer pageSize, String sort);
}
