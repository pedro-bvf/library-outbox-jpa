package com.library.books.driven.repositories.mappers;

import com.library.books.domain.AuthorDTO;
import com.library.books.domain.BookDTO;
import com.library.books.domain.PublisherDTO;
import com.library.books.driven.repositories.models.AuthorMO;
import com.library.books.driven.repositories.models.BookMO;
import com.library.books.driven.repositories.models.PublisherMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookRepositoryMapper {

  BookDTO toBookDTO(BookMO bookMO);

  AuthorDTO toAuthorDTO(AuthorMO authorMO);

  PublisherDTO toPublisherDTO(PublisherMO publisherMO);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "publicId", ignore = true)
  @Mapping(target = "publisher", ignore = true)
  @Mapping(target = "authors", ignore = true)
  void updateBookMOFromDTO(BookDTO changes, @MappingTarget BookMO bookMO);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "publisher", ignore = true)
  @Mapping(target = "authors", ignore = true)
  BookMO toBookMO(BookDTO book);
}
