package com.library.books.driven.repositories.adapters;

import com.library.books.application.ports.driven.AuthorRepositoryPort;
import com.library.books.domain.AuthorDTO;
import com.library.books.driven.repositories.mappers.AuthorRepositoryMapper;
import com.library.books.driven.repositories.repository.AuthorMOJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AuthorRepositoryAdapter implements AuthorRepositoryPort {
  private final AuthorMOJpaRepository authorRepository;
  private final AuthorRepositoryMapper authorMapper;

  @Override
  public List<AuthorDTO> findAllById(List<UUID> authorIds) {
    var authorMOs = authorRepository.findAllById(authorIds);
    return authorMapper.listAuthorToAuthorDTO(authorMOs);
  }
}
