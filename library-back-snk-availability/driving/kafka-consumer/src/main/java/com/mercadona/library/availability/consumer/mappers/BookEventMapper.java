package com.mercadona.library.availability.consumer.mappers;

import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventValue;
import com.mercadona.library.availability.domain.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookEventMapper {
  BookDTO toBookDTO(BookAvailabilityEventValue event);
}
