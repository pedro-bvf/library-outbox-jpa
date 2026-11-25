package com.mercadona.library.books.application.ports.driven;

import com.mercadona.library.books.domain.PublisherDTO;

import java.util.Optional;
import java.util.UUID;

public interface PublisherRepositoryPort {
  Optional<PublisherDTO> findById(UUID id);
}
