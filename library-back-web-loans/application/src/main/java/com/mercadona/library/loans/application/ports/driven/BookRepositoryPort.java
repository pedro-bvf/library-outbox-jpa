package com.mercadona.library.loans.application.ports.driven;

import java.util.List;
import java.util.UUID;

public interface BookRepositoryPort {
  boolean existsById(UUID id);

  List<UUID> findExistingIds(List<UUID> ids);
}
