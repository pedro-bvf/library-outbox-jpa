package com.mercadona.library.books.driven.repositories.adapters;

import com.mercadona.library.books.application.ports.driven.PublisherRepositoryPort;
import com.mercadona.library.books.domain.PublisherDTO;
import com.mercadona.library.books.driven.repositories.mappers.PublisherRepositoryMapper;
import com.mercadona.library.books.driven.repositories.repository.PublisherMOJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PublisherRepositoryAdapter implements PublisherRepositoryPort {
  private final PublisherMOJpaRepository publisherRepository;
  private final PublisherRepositoryMapper publisherMapper;

  @Override
  public Optional<PublisherDTO> findById(UUID id) {
    return publisherRepository.findById(id).map(publisherMapper::toPublisherDTO);
  }

}
