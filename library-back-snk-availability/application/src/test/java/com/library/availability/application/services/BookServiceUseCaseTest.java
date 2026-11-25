package com.library.availability.application.services;

import static org.mockito.BDDMockito.then;

import com.library.availability.application.ports.driven.BookRepositoryPort;
import com.library.availability.domain.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceUseCaseTest {

  private static final long PUBLIC_ID = 123L;

  @Mock
  private BookRepositoryPort bookRepositoryPort;

  private BookServiceUseCase bookServiceUseCase;

  @BeforeEach
  void before() {
    bookServiceUseCase = new BookServiceUseCase(bookRepositoryPort);
  }

  @Test
  @DisplayName("Should save the book availability")
  void shouldProcessBook() {
    var book = new BookDTO(PUBLIC_ID, true);

    bookServiceUseCase.process(book);

    then(bookRepositoryPort).should().save(book);
  }

  @Test
  @DisplayName("Should delete the book availability")
  void shouldDeleteBook() {
    bookServiceUseCase.delete(PUBLIC_ID);

    then(bookRepositoryPort).should().deleteById(PUBLIC_ID);
  }
}
