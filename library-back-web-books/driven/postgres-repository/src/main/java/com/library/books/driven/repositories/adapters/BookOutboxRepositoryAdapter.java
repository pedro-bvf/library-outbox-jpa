package com.library.books.driven.repositories.adapters;

import com.library.books.application.ports.driven.BookOutboxPublisherPort;
import com.library.books.domain.BookDTO;
import com.library.books.driven.repositories.models.BookOutboxEntity;
import com.library.books.driven.repositories.outbox.BookOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookOutboxRepositoryAdapter implements BookOutboxPublisherPort {
  private final BookOutboxRepository repository;

  @Override
  public void saveBookOutbox(BookDTO book) {
    repository.save(BookOutboxEntity.availabilityChanged(book.publicId(), !Boolean.TRUE.equals(book.isDeleted())));
  }

  @Override
  public void deleteBookOutbox(BookDTO book) {
    repository.save(BookOutboxEntity.tombstone(book.publicId()));
  }
}
