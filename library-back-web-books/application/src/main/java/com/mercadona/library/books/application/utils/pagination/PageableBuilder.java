package com.mercadona.library.books.application.utils.pagination;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@UtilityClass
public class PageableBuilder {
  private static final int DEFAULT_PAGE_SIZE = 10;

  public static Pageable toPageable(int firstPage, int pageSize) {
    return toPageable(firstPage, pageSize, null);
  }

  public static Pageable toPageable(int firstPage, int pageSize, String sort) {
    var normalizedPage = Math.max(firstPage - 1, 0);
    var normalizedSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    return PageRequest.of(normalizedPage, normalizedSize, SortBuilder.of(sort));
  }
}
