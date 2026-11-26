package com.library.books.driven.repositories.models;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_availability_outbox")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookOutboxEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "public_id", nullable = false)
  private Long publicId;

  @Column(name = "event_type", nullable = false)
  private String eventType;

  @Setter
  @Column(name = "processed_at")
  private Instant processedAt;

  private BookOutboxEntity(Long publicId, String eventType) {
    this.publicId = publicId;
    this.eventType = eventType;
  }

  public static BookOutboxEntity availabilityChanged(Long publicId, boolean available) {
    return new BookOutboxEntity(publicId, available ? BookOutboxEventType.AVAILABLE.name() : BookOutboxEventType.UNAVAILABLE.name());
  }

  public static BookOutboxEntity deleted(Long publicId) {
    return new BookOutboxEntity(publicId, BookOutboxEventType.DELETED.name());
  }
}
