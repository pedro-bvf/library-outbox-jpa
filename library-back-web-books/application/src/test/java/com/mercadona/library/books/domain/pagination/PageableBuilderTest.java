package com.mercadona.library.books.domain.pagination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mercadona.library.books.application.utils.pagination.PageableBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

class PageableBuilderTest {
  @Test
  void toPageable_convertsFirstPageAndDefaultsToUnsorted() {
    var pageable = PageableBuilder.toPageable(2, 10);

    assertEquals(1, pageable.getPageNumber());
    assertEquals(10, pageable.getPageSize());
    assertEquals(Sort.unsorted(), pageable.getSort());
  }

  @Test
  void toPageable_convertsFirstPageAndParsesSort() {
    var pageable = PageableBuilder.toPageable(2, 10, "+title");

    assertEquals(1, pageable.getPageNumber());
    assertEquals(10, pageable.getPageSize());
    assertEquals(Sort.by(Sort.Order.asc("title")), pageable.getSort());
  }

  @Test
  void toPageable_normalizesZeroPageSizeToDefault() {
    var pageable = PageableBuilder.toPageable(1, 0);

    assertEquals(10, pageable.getPageSize());
  }

  @Test
  void toPageable_normalizesNegativePageSizeToDefault() {
    var pageable = PageableBuilder.toPageable(1, -5);

    assertEquals(10, pageable.getPageSize());
  }
}
