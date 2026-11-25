package com.library.availability.consumer.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookEventMapperTest {

  private BookEventMapper mapper;

  @BeforeEach
  void before() {
    mapper = new BookEventMapperImpl();
  }

  @Test
  void shouldMapEventToBookDTO() {
    var event = BookAvailabilityEventValue.newBuilder()
      .setPublicId(999L)
      .setAvailable(true)
      .build();

    var book = mapper.toBookDTO(event);

    assertThat(book.publicId()).isEqualTo(999L);
    assertThat(book.available()).isTrue();
  }
}
