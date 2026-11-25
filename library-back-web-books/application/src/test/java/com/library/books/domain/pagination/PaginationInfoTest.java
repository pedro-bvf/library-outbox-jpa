package com.library.books.domain.pagination;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.library.books.application.utils.pagination.PaginationInfo;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

class PaginationInfoTest {

  @Test
  void from_middlePage_hasBothNextAndPreviousPage() {
    var page = new PageImpl<>(List.of("a", "b"), PageRequest.of(1, 2), 10);

    var result = PaginationInfo.from(page);

    assertThat(result.requestedPage()).isEqualTo(2);
    assertThat(result.requestedSize()).isEqualTo(2);
    assertThat(result.retrievedResults()).isEqualTo(2);
    assertThat(result.totalResults()).isEqualTo(10);
    assertThat(result.totalPages()).isEqualTo(5);
    assertThat(result.nextPage()).isEqualTo(3);
    assertThat(result.previousPage()).isEqualTo(1);
  }

  @Test
  void from_firstPage_hasNoPreviousPage() {
    var page = new PageImpl<>(List.of("a", "b"), PageRequest.of(0, 2), 10);

    var result = PaginationInfo.from(page);

    assertThat(result.requestedPage()).isEqualTo(1);
    assertThat(result.previousPage()).isNull();
    assertThat(result.nextPage()).isEqualTo(2);
  }

  @Test
  void from_lastPage_hasNoNextPage() {
    var page = new PageImpl<>(List.of("a", "b"), PageRequest.of(4, 2), 10);

    var result = PaginationInfo.from(page);

    assertThat(result.requestedPage()).isEqualTo(5);
    assertThat(result.nextPage()).isNull();
    assertThat(result.previousPage()).isEqualTo(4);
  }

  @Test
  void from_singlePage_hasNoNextNorPreviousPage() {
    var page = new PageImpl<>(List.of("a"), PageRequest.of(0, 10), 1);

    var result = PaginationInfo.from(page);

    assertThat(result.totalPages()).isEqualTo(1);
    assertThat(result.nextPage()).isNull();
    assertThat(result.previousPage()).isNull();
  }
}
