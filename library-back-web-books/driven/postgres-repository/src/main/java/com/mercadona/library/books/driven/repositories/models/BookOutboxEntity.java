package com.mercadona.library.books.driven.repositories.models;

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

  @Column(nullable = false)
  private Boolean available;

  @Column(nullable = false)
  private Boolean tombstone;

  @Setter
  @Column(name = "processed_at")
  private Instant processedAt;

  private BookOutboxEntity(Long publicId, Boolean available, Boolean tombstone) {
    this.publicId = publicId;
    this.available = available;
    this.tombstone = tombstone;
  }

  public static BookOutboxEntity availabilityChanged(Long publicId, boolean available) {
    return new BookOutboxEntity(publicId, available, false);
  }

  public static BookOutboxEntity tombstone(Long publicId) {
    return new BookOutboxEntity(publicId, false, true);
  }
}
