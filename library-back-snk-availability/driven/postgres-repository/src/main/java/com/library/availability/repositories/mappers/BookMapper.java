package com.library.availability.repositories.mappers;

import com.library.availability.domain.BookDTO;
import com.library.availability.repositories.models.BookMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
  BookDTO fromModel(BookMO model);

  @Mapping(target = "id", ignore = true)
  BookMO toModel(BookDTO book);
}
