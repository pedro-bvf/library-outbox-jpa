package com.mercadona.library.books.domain.pagination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mercadona.library.books.application.utils.pagination.SortBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

class SortBuilderTest {
  @Test
  void acceptsPrefixAndCommaFormats() {
    assertEquals(Sort.by(Sort.Order.asc("id"), Sort.Order.desc("title")),
      SortBuilder.of("+id;-title"));
    assertEquals(Sort.by(Sort.Order.desc("id"), Sort.Order.asc("title")),
      SortBuilder.of("id,DESC;title,ASC"));
  }

  @Test
  void rejectsAmbiguousAndEmptyFields() {
    assertThrows(IllegalArgumentException.class, () -> SortBuilder.of("-id,ASC"));
    assertThrows(IllegalArgumentException.class, () -> SortBuilder.of("+id;title,DESC"));
    assertThrows(IllegalArgumentException.class, () -> SortBuilder.of("-"));
  }
}
