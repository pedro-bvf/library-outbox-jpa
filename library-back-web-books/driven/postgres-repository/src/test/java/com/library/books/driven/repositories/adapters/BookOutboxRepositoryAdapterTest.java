package com.library.books.driven.repositories.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.library.books.domain.BookDTO;
import com.library.books.driven.repositories.models.BookOutboxEntity;
import com.library.books.driven.repositories.outbox.BookOutboxRepository;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookOutboxRepositoryAdapterTest {

  @Mock private BookOutboxRepository repository;

  @InjectMocks private BookOutboxRepositoryAdapter adapter;

  @Test
  void saveBookOutbox_marksBookAsAvailableWhenNotDeleted() {
    var book = bookWithDeletedFlag(null);

    adapter.saveBookOutbox(book);

    var saved = capturedEntity();
    assertThat(saved.getPublicId()).isEqualTo(book.publicId());
    assertThat(saved.getAvailable()).isTrue();
    assertThat(saved.getTombstone()).isFalse();
  }

  @Test
  void saveBookOutbox_marksBookAsUnavailableWhenDeleted() {
    var book = bookWithDeletedFlag(true);

    adapter.saveBookOutbox(book);

    var saved = capturedEntity();
    assertThat(saved.getAvailable()).isFalse();
    assertThat(saved.getTombstone()).isFalse();
  }

  @Test
  void deleteBookOutbox_savesTombstone() {
    var book = bookWithDeletedFlag(null);

    adapter.deleteBookOutbox(book);

    var saved = capturedEntity();
    assertThat(saved.getPublicId()).isEqualTo(book.publicId());
    assertThat(saved.getTombstone()).isTrue();
  }

  private BookOutboxEntity capturedEntity() {
    var captor = ArgumentCaptor.forClass(BookOutboxEntity.class);
    verify(repository).save(captor.capture());
    return captor.getValue();
  }

  private BookDTO bookWithDeletedFlag(Boolean isDeleted) {
    return new BookDTO(
      UUID.randomUUID(), 42L, "Title", "cover-url", "1st", BigDecimal.TEN, "en", "epub-url",
      false, isDeleted, null, Set.of());
  }
}
