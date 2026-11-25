package com.library.books.driving.controllers.error;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.books.application.exceptions.BadRequestException;
import com.library.books.application.exceptions.ConflictException;
import com.library.books.application.exceptions.NotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CustomControllerAdviceTest {

  private final CustomControllerAdvice customControllerAdvice = new CustomControllerAdvice();

  @Test
  void handleExampleNotFoundException_buildsErrorResourceFromException() {
    var id = UUID.randomUUID();
    var exception = NotFoundException.of("Book with id %s not found.", id);

    var response = customControllerAdvice.handleExampleNotFoundException(exception);

    var error = response.getError();
    assertThat(error.getCode()).isEqualTo("C01");
    assertThat(error.getDescription()).isEqualTo(exception.getMessage());
    assertThat(error.getDetails()).containsExactly(exception.getMessage());
  }

  @Test
  void handleInvalidBookException_buildsErrorResourceFromException() {
    var exception = BadRequestException.of("Publisher is required to create a book.");

    var response = customControllerAdvice.handleBadRequestException(exception);

    var error = response.getError();
    assertThat(error.getCode()).isEqualTo("C02");
    assertThat(error.getDescription()).isEqualTo(exception.getMessage());
    assertThat(error.getDetails()).containsExactly(exception.getMessage());
  }

  @Test
  void handleConflictException_buildsErrorResourceFromException() {
    var exception = ConflictException.of("A book with publicId %s already exists.", 1L);

    var response = customControllerAdvice.handleConflictException(exception);

    var error = response.getError();
    assertThat(error.getCode()).isEqualTo("C03");
    assertThat(error.getDescription()).isEqualTo(exception.getMessage());
    assertThat(error.getDetails()).containsExactly(exception.getMessage());
  }
}
