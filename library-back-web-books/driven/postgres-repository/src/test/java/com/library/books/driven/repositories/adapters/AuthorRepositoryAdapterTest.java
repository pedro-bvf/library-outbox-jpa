package com.library.books.driven.repositories.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.library.books.domain.AuthorDTO;
import com.library.books.driven.repositories.mappers.AuthorRepositoryMapper;
import com.library.books.driven.repositories.models.AuthorMO;
import com.library.books.driven.repositories.repository.AuthorMOJpaRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthorRepositoryAdapterTest {

  @Mock private AuthorMOJpaRepository authorRepository;

  @Mock private AuthorRepositoryMapper authorMapper;

  @InjectMocks private AuthorRepositoryAdapter authorRepositoryAdapter;

  @Test
  void findAllById_returnsMappedAuthorsForGivenIds() {
    var authorId = UUID.randomUUID();
    var authorMO = new AuthorMO();
    authorMO.setId(authorId);
    var authorDTO = new AuthorDTO(authorId, 1L, "Name", null, null);
    when(authorRepository.findAllById(List.of(authorId))).thenReturn(List.of(authorMO));
    when(authorMapper.listAuthorToAuthorDTO(List.of(authorMO))).thenReturn(List.of(authorDTO));

    var result = authorRepositoryAdapter.findAllById(List.of(authorId));

    assertThat(result).containsExactly(authorDTO);
  }

  @Test
  void findAllById_returnsEmptyListWhenNoAuthorsAreFound() {
    var authorId = UUID.randomUUID();
    when(authorRepository.findAllById(List.of(authorId))).thenReturn(List.of());
    when(authorMapper.listAuthorToAuthorDTO(List.of())).thenReturn(List.of());

    var result = authorRepositoryAdapter.findAllById(List.of(authorId));

    assertThat(result).isEmpty();
  }
}
