package com.mercadona.library.availability.repositories.mappers;

import com.mercadona.library.availability.domain.BookDTO;
import com.mercadona.library.availability.repositories.models.BookMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
  BookDTO fromModel(BookMO model);

  @Mapping(target = "id", ignore = true)
  BookMO toModel(BookDTO book);
}
