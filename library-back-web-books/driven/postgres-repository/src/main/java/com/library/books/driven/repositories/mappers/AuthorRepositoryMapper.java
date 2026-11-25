package com.library.books.driven.repositories.mappers;

import com.library.books.domain.AuthorDTO;
import com.library.books.driven.repositories.models.AuthorMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorRepositoryMapper {
  List<AuthorDTO> listAuthorToAuthorDTO(List<AuthorMO> listAuthor);

  @Mapping(target = "books", ignore = true)
  AuthorMO toAuthorMO(AuthorDTO author);
}
