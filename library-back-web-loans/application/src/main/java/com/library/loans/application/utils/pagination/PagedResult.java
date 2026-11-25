package com.library.loans.application.utils.pagination;

import java.util.List;

public record PagedResult<T>(
  List<T> data,
  PaginationInfo pagination
) {
}
