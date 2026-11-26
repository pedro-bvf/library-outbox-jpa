package com.library.books.driven.repositories.outbox;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventKey;
import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventValue;
import com.library.books.driven.repositories.models.BookOutboxEntity;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class BookOutboxProducerTest {

  private static final String TOPIC = "library.availability.event.restrictedout.v0.canonical.cpd";

  @Mock private BookOutboxRepository repository;

  @Mock private KafkaTemplate<BookAvailabilityEventKey, BookAvailabilityEventValue> kafka;

  private BookOutboxProducer producer;

  @BeforeEach
  void setUp() {
    producer = new BookOutboxProducer(repository, kafka);
    ReflectionTestUtils.setField(producer, "topic", TOPIC);
  }

  @Test
  void publishNext_doesNothingWhenThereIsNoPendingEvent() {
    when(repository.findFirstByProcessedAtIsNullOrderByIdAsc()).thenReturn(Optional.empty());

    producer.publishNext();

    verify(kafka, never()).send(any(), any(), any());
  }

  @Test
  void publishNext_publishesAvailabilityAndMarksTheRowAsProcessedOnSuccess() {
    var event = BookOutboxEntity.availabilityChanged(42L, true);
    when(repository.findFirstByProcessedAtIsNullOrderByIdAsc()).thenReturn(Optional.of(event));
    when(kafka.send(eq(TOPIC), any(), any())).thenReturn(CompletableFuture.completedFuture(null));

    producer.publishNext();

    var expectedKey = BookAvailabilityEventKey.newBuilder().setPublicId(42L).build();
    var expectedValue =
      BookAvailabilityEventValue.newBuilder().setPublicId(42L).setAvailable(true).build();
    verify(kafka).send(TOPIC, expectedKey, expectedValue);
    verify(repository).save(event);
    assertThat(event.getProcessedAt()).isNotNull();
  }

  @Test
  void publishNext_publishesTombstoneWhenEventIsTombstone() {
    var event = BookOutboxEntity.deleted(43L);
    when(repository.findFirstByProcessedAtIsNullOrderByIdAsc()).thenReturn(Optional.of(event));
    when(kafka.send(eq(TOPIC), any(), any())).thenReturn(CompletableFuture.completedFuture(null));

    producer.publishNext();

    var expectedKey = BookAvailabilityEventKey.newBuilder().setPublicId(43L).build();
    verify(kafka).send(TOPIC, expectedKey, null);
    verify(repository).save(event);
    assertThat(event.getProcessedAt()).isNotNull();
  }

  @Test
  void publishNext_leavesTheRowUnmarkedWhenPublishingFails() {
    var event = BookOutboxEntity.availabilityChanged(44L, true);
    when(repository.findFirstByProcessedAtIsNullOrderByIdAsc()).thenReturn(Optional.of(event));
    when(kafka.send(eq(TOPIC), any(), any()))
      .thenReturn(CompletableFuture.failedFuture(new RuntimeException("broker unreachable")));

    assertThatThrownBy(() -> producer.publishNext()).isInstanceOf(IllegalStateException.class);

    verify(repository, never()).save(any());
    assertThat(event.getProcessedAt()).isNull();
  }
}
