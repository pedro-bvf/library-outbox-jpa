package com.library.loans.driven.repositories.adapters;

import com.library.loans.application.ports.driven.CustomerRepositoryPort;
import com.library.loans.driven.repositories.CustomerMOJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {
  private final CustomerMOJpaRepository repository;

  @Override
  public boolean existsById(UUID id) {
    return repository.existsById(id);
  }
}
