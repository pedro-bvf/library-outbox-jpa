package com.mercadona.library.availability.repositories.mappers;

import com.mercadona.library.availability.domain.BookDTO;
import com.mercadona.library.availability.repositories.models.BookMO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-25T15:35:15+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.5.1 (Eclipse Adoptium)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO fromModel(BookMO model) {
        if ( model == null ) {
            return null;
        }

        Long publicId = null;
        boolean available = false;

        publicId = model.getPublicId();
        available = model.isAvailable();

        BookDTO bookDTO = new BookDTO( publicId, available );

        return bookDTO;
    }

    @Override
    public BookMO toModel(BookDTO book) {
        if ( book == null ) {
            return null;
        }

        BookMO bookMO = new BookMO();

        bookMO.setPublicId( book.publicId() );
        bookMO.setAvailable( book.available() );

        return bookMO;
    }
}
