package com.mercadona.library.loans.application.utils.pagination;

import org.springframework.data.domain.Page;

public record PaginationInfo(
  int requestedPage,
  int requestedSize,
  int retrievedResults,
  long totalResults,
  int totalPages,
  Integer nextPage,
  Integer previousPage
) {
  public static PaginationInfo from(Page<?> page) {
    int currentPage = page.getNumber() + 1;
    int totalPages = page.getTotalPages();

    return new PaginationInfo(
      currentPage,
      page.getSize(),
      page.getNumberOfElements(),
      page.getTotalElements(),
      totalPages,
      currentPage < totalPages ? currentPage + 1 : null,
      currentPage > 1 ? currentPage - 1 : null
    );
  }
}
