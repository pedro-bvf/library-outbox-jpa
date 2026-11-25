package com.library.availability.repositories.adapters;

import com.library.availability.application.ports.driven.BookRepositoryPort;
import com.library.availability.domain.BookDTO;
import com.library.availability.repositories.BookMOJpaRepository;
import com.library.availability.repositories.mappers.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookRepositoryPort {
  private final BookMOJpaRepository repository;
  private final BookMapper mapper;

  @Override
  public void save(BookDTO book) {
    var bookMO = mapper.toModel(book);

    repository.findByPublicId(book.publicId())
      .ifPresent(existing -> bookMO.setId(existing.getId()));

    repository.save(bookMO);
  }

  @Override
  public void deleteById(Long publicId) {
    repository.deleteByPublicId(publicId);
  }

}
