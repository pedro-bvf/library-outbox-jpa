package com.library.books.application.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {
  @Test
  void of_formatsMessageWithGivenId() {
    var id = UUID.randomUUID();

    var exception = NotFoundException.of("Book with id %s not found.", id);

    assertThat(exception.getMessage()).isEqualTo("Book with id " + id + " not found.");
  }

  @Test
  void of_setsBusinessErrorCode() {
    var exception = NotFoundException.of("Book with id %s not found.", UUID.randomUUID());

    assertThat(exception.getErrorCode()).isEqualTo("C01");
    assertThat(exception).isInstanceOf(BusinessException.class);
  }
}
