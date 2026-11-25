package com.mercadona.library.books.application.ports.driven;

import com.mercadona.library.books.domain.AuthorDTO;

import java.util.List;
import java.util.UUID;

public interface AuthorRepositoryPort {
  List<AuthorDTO> findAllById(List<UUID> authorIds);
}
