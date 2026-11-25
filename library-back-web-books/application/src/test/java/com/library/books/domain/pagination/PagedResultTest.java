package com.library.books.domain.pagination;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.library.books.application.utils.pagination.PagedResult;
import com.library.books.application.utils.pagination.PaginationInfo;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

class PagedResultTest {

  @Test
  void from_wrapsPageContentAndPaginationInfo() {
    var page = new PageImpl<>(List.of("a", "b"), PageRequest.of(0, 2), 5);

    var result = PagedResult.from(page);

    assertThat(result.data()).containsExactly("a", "b");
    assertThat(result.pagination()).isEqualTo(PaginationInfo.from(page));
  }
}
