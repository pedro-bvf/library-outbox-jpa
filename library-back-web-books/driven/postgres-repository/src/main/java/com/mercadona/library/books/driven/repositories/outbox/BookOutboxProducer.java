package com.mercadona.library.books.driven.repositories.outbox;

import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventKey;
import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventValue;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookOutboxProducer {
  private final BookOutboxRepository repository;
  private final KafkaTemplate<BookAvailabilityEventKey, BookAvailabilityEventValue> kafka;

  @Value("${books.kafka.topic}")
  private String topic;

  @Scheduled(fixedDelayString = "${books.outbox.poll-delay-ms:500}")
  @Transactional
  public void publishNext() {
    var pending = repository.findFirstByProcessedAtIsNullOrderByIdAsc();
    if (pending.isEmpty()) return;

    var event = pending.get();
    log.debug("Publishing outbox event {} for book {}", event.getId(), event.getPublicId());

    var key = BookAvailabilityEventKey.newBuilder().setPublicId(event.getPublicId()).build();
    BookAvailabilityEventValue value = event.getTombstone() ? null
        : BookAvailabilityEventValue.newBuilder()
            .setPublicId(event.getPublicId())
            .setAvailable(event.getAvailable())
            .build();

    try {
      kafka.send(topic, key, value).get();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Interrupted while publishing event {} for book {}", event.getId(), event.getPublicId(), e);
      throw new IllegalStateException("Interrupted while publishing event " + event.getId(), e);
    } catch (ExecutionException e) {
      log.error("Failed to publish event {} for book {}", event.getId(), event.getPublicId(), e.getCause());
      throw new IllegalStateException("Failed to publish event " + event.getId(), e.getCause());
    }
    event.setProcessedAt(Instant.now());
    repository.save(event);
    log.info("Published and marked outbox event {} for book {} as processed ({})",
        event.getId(), event.getPublicId(),
        event.getTombstone() ? "tombstone" : "available=" + event.getAvailable());
  }
}
