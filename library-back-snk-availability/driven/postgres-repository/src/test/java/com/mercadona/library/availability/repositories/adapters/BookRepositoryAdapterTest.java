package com.mercadona.library.availability.repositories.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.mercadona.library.availability.domain.BookDTO;
import com.mercadona.library.availability.repositories.BookMOJpaRepository;
import com.mercadona.library.availability.repositories.mappers.BookMapper;
import com.mercadona.library.availability.repositories.models.BookMO;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookRepositoryAdapterTest {

  private static final long PUBLIC_ID = 123L;

  @Mock
  private BookMOJpaRepository repository;

  @Mock
  private BookMapper mapper;

  @InjectMocks
  private BookRepositoryAdapter bookRepositoryAdapter;

  @Test
  @DisplayName("Should insert a new row when the book does not exist yet")
  void shouldInsertWhenNotExisting() {
    var book = new BookDTO(PUBLIC_ID, true);
    var bookMO = new BookMO();
    bookMO.setPublicId(PUBLIC_ID);
    bookMO.setAvailable(true);

    given(mapper.toModel(book)).willReturn(bookMO);
    given(repository.findByPublicId(PUBLIC_ID)).willReturn(Optional.empty());

    bookRepositoryAdapter.save(book);

    then(repository).should().save(bookMO);
    assertThat(bookMO.getId()).isNull();
  }

  @Test
  @DisplayName("Should update the existing row keeping its id when the book already exists")
  void shouldUpdateWhenExisting() {
    var book = new BookDTO(PUBLIC_ID, false);
    var bookMO = new BookMO();
    bookMO.setPublicId(PUBLIC_ID);
    bookMO.setAvailable(false);
    var existingId = UUID.randomUUID();
    var existing = new BookMO();
    existing.setId(existingId);
    existing.setPublicId(PUBLIC_ID);

    given(mapper.toModel(book)).willReturn(bookMO);
    given(repository.findByPublicId(PUBLIC_ID)).willReturn(Optional.of(existing));

    bookRepositoryAdapter.save(book);

    then(repository).should().save(bookMO);
    assertThat(bookMO.getId()).isEqualTo(existingId);
  }

  @Test
  @DisplayName("Should delete the book availability by publicId")
  void shouldDeleteById() {
    bookRepositoryAdapter.deleteById(PUBLIC_ID);

    then(repository).should().deleteByPublicId(PUBLIC_ID);
  }
}
