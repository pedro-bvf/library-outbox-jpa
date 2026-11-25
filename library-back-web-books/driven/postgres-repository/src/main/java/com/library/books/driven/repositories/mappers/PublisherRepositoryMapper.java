package com.library.books.driven.repositories.mappers;

import com.library.books.domain.PublisherDTO;
import com.library.books.driven.repositories.models.PublisherMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PublisherRepositoryMapper {
  PublisherDTO toPublisherDTO(PublisherMO publisherMO);

  @Mapping(target = "books", ignore = true)
  PublisherMO toPublisherMO(PublisherDTO publisher);
}
