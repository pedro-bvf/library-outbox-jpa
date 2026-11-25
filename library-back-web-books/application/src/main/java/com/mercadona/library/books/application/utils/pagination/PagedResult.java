package com.mercadona.library.books.application.utils.pagination;

import java.util.List;
import org.springframework.data.domain.Page;

public record PagedResult<T>(
  List<T> data,
  PaginationInfo pagination
) {
  public static <T> PagedResult<T> from(Page<T> page) {
    return new PagedResult<>(page.getContent(), PaginationInfo.from(page));
  }
}
