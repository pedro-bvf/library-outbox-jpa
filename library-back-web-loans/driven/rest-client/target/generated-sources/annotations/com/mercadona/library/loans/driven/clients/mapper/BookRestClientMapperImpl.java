package com.mercadona.library.loans.driven.clients.mapper;

import com.mercadona.library.loans.application.utils.pagination.PagedResult;
import com.mercadona.library.loans.application.utils.pagination.PaginationInfo;
import com.mercadona.library.loans.domain.BookCatalogEntry;
import com.mercadona.library.loans.driven.clients.dto.BookResource;
import com.mercadona.library.loans.driven.clients.dto.BookResourceCollectionResponse;
import com.mercadona.library.loans.driven.clients.dto.Pagination;
import com.mercadona.library.loans.driven.clients.dto.PublisherResource;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-25T15:18:32+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.5.1 (Eclipse Adoptium)"
)
@Component
public class BookRestClientMapperImpl implements BookRestClientMapper {

    @Override
    public BookCatalogEntry toDomain(BookResource resource) {
        if ( resource == null ) {
            return null;
        }

        List<String> authors = null;
        String publisher = null;
        UUID id = null;
        Long publicId = null;
        String title = null;
        String edition = null;
        String coverUrl = null;
        String epubUrl = null;
        Boolean isDigital = null;
        String languages = null;
        BigDecimal price = null;

        authors = toAuthorNames( resource.authors() );
        publisher = resourcePublisherName( resource );
        id = resource.id();
        publicId = resource.publicId();
        title = resource.title();
        edition = resource.edition();
        coverUrl = resource.coverUrl();
        epubUrl = resource.epubUrl();
        isDigital = resource.isDigital();
        languages = resource.languages();
        price = resource.price();

        BookCatalogEntry bookCatalogEntry = new BookCatalogEntry( id, publicId, title, edition, coverUrl, epubUrl, isDigital, languages, price, authors, publisher );

        return bookCatalogEntry;
    }

    @Override
    public PagedResult<BookCatalogEntry> toDomain(BookResourceCollectionResponse response) {
        if ( response == null ) {
            return null;
        }

        List<BookCatalogEntry> data = null;
        PaginationInfo pagination = null;

        data = toDomain( response.data() );
        pagination = toDomain( response.pagination() );

        PagedResult<BookCatalogEntry> pagedResult = new PagedResult<BookCatalogEntry>( data, pagination );

        return pagedResult;
    }

    @Override
    public PaginationInfo toDomain(Pagination pagination) {
        if ( pagination == null ) {
            return null;
        }

        int requestedPage = 0;
        int requestedSize = 0;
        int retrievedResults = 0;
        long totalResults = 0L;
        Integer nextPage = null;
        Integer previousPage = null;

        if ( pagination.requestedPage() != null ) {
            requestedPage = pagination.requestedPage();
        }
        if ( pagination.requestedSize() != null ) {
            requestedSize = pagination.requestedSize();
        }
        if ( pagination.retrievedResults() != null ) {
            retrievedResults = pagination.retrievedResults();
        }
        if ( pagination.totalResults() != null ) {
            totalResults = pagination.totalResults();
        }
        if ( pagination.nextPage() != null ) {
            nextPage = Integer.parseInt( pagination.nextPage() );
        }
        if ( pagination.previousPage() != null ) {
            previousPage = Integer.parseInt( pagination.previousPage() );
        }

        int totalPages = 0;

        PaginationInfo paginationInfo = new PaginationInfo( requestedPage, requestedSize, retrievedResults, totalResults, totalPages, nextPage, previousPage );

        return paginationInfo;
    }

    private String resourcePublisherName(BookResource bookResource) {
        PublisherResource publisher = bookResource.publisher();
        if ( publisher == null ) {
            return null;
        }
        return publisher.name();
    }
}
