package com.library.books.driven.repositories.outbox;

import com.library.books.driven.repositories.models.BookOutboxEntity;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface BookOutboxRepository extends JpaRepository<BookOutboxEntity, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<BookOutboxEntity> findFirstByProcessedAtIsNullOrderByIdAsc();
}
