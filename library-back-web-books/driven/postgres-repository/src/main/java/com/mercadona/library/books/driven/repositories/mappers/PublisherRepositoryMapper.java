package com.mercadona.library.books.driven.repositories.mappers;

import com.mercadona.library.books.domain.PublisherDTO;
import com.mercadona.library.books.driven.repositories.models.PublisherMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PublisherRepositoryMapper {
  PublisherDTO toPublisherDTO(PublisherMO publisherMO);

  @Mapping(target = "books", ignore = true)
  PublisherMO toPublisherMO(PublisherDTO publisher);
}
