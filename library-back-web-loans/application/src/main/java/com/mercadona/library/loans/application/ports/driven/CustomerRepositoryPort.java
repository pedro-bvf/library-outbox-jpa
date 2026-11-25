package com.mercadona.library.loans.application.ports.driven;

import java.util.UUID;

public interface CustomerRepositoryPort {
  boolean existsById(UUID id);
}
