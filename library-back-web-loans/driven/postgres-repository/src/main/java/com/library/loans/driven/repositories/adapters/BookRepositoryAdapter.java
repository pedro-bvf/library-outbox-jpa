package com.library.loans.driven.repositories.adapters;

import com.library.loans.application.ports.driven.BookRepositoryPort;
import com.library.loans.driven.repositories.BookMOJpaRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookRepositoryAdapter implements BookRepositoryPort {
  private final BookMOJpaRepository repository;

  @Override
  public boolean existsById(java.util.UUID id) {
    return repository.existsById(id);
  }

  @Override
  public List<UUID> findExistingIds(List<UUID> ids) {
    return repository.findExistingIds(ids);
  }
}
