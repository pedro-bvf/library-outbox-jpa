package com.library.books.application.ports.driven;

import com.library.books.domain.AuthorDTO;

import java.util.List;
import java.util.UUID;

public interface AuthorRepositoryPort {
  List<AuthorDTO> findAllById(List<UUID> authorIds);
}
