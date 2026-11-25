package com.mercadona.library.availability.repositories.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import com.mercadona.library.availability.domain.BookDTO;
import com.mercadona.library.availability.repositories.models.BookMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookMapperTest {

  private BookMapper mapper;

  @BeforeEach
  void setup() {
    mapper = new BookMapperImpl();
  }

  @Test
  @DisplayName("Should map a model to a BookDTO")
  void shouldMapModelToBookDTO() {
    var model = new BookMO();
    model.setPublicId(123L);
    model.setAvailable(true);

    var book = mapper.fromModel(model);

    assertThat(book.publicId()).isEqualTo(123L);
    assertThat(book.available()).isTrue();
  }

  @Test
  @DisplayName("Should map a BookDTO to a model")
  void shouldMapBookDTOToModel() {
    var book = new BookDTO(123L, true);

    var model = mapper.toModel(book);

    assertThat(model.getPublicId()).isEqualTo(123L);
    assertThat(model.isAvailable()).isTrue();
  }
}
