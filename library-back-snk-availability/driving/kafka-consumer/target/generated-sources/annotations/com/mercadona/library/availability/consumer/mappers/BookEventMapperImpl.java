package com.mercadona.library.availability.consumer.mappers;

import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventValue;
import com.mercadona.library.availability.domain.BookDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-25T15:35:14+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.5.1 (Eclipse Adoptium)"
)
@Component
public class BookEventMapperImpl implements BookEventMapper {

    @Override
    public BookDTO toBookDTO(BookAvailabilityEventValue event) {
        if ( event == null ) {
            return null;
        }

        Long publicId = null;
        boolean available = false;

        publicId = event.getPublicId();
        available = event.getAvailable();

        BookDTO bookDTO = new BookDTO( publicId, available );

        return bookDTO;
    }
}
