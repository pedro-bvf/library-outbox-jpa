package com.mercadona.library.loans.driving.controllers.mappers;

import com.mercadona.library.api.web_loans.model.BookCollectionResponse;
import com.mercadona.library.api.web_loans.model.BookResource;
import com.mercadona.library.api.web_loans.model.Pagination;
import com.mercadona.library.loans.application.utils.pagination.PagedResult;
import com.mercadona.library.loans.application.utils.pagination.PaginationInfo;
import com.mercadona.library.loans.domain.BookCatalogEntry;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-25T15:18:30+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.5.1 (Eclipse Adoptium)"
)
@Component
public class BooksDTOMapperImpl implements BooksDTOMapper {

    @Override
    public BookCollectionResponse toBookCollectionResponse(PagedResult<BookCatalogEntry> page) {
        if ( page == null ) {
            return null;
        }

        BookCollectionResponse bookCollectionResponse = new BookCollectionResponse();

        bookCollectionResponse.setData( toBookCollection( page.data() ) );
        bookCollectionResponse.setPagination( paginationInfoToPagination( page.pagination() ) );

        return bookCollectionResponse;
    }

    @Override
    public BookResource toBookResource(BookCatalogEntry entry) {
        if ( entry == null ) {
            return null;
        }

        BookResource bookResource = new BookResource();

        bookResource.setId( entry.id() );
        bookResource.setPublicId( entry.publicId() );
        bookResource.setTitle( entry.title() );
        bookResource.setEdition( entry.edition() );
        bookResource.setCoverUrl( entry.coverUrl() );
        bookResource.setEpubUrl( entry.epubUrl() );
        bookResource.setIsDigital( entry.isDigital() );
        bookResource.setLanguages( entry.languages() );
        bookResource.setPrice( entry.price() );
        List<String> list = entry.authors();
        if ( list != null ) {
            bookResource.setAuthors( new ArrayList<String>( list ) );
        }
        bookResource.setPublisher( entry.publisher() );

        return bookResource;
    }

    protected Pagination paginationInfoToPagination(PaginationInfo paginationInfo) {
        if ( paginationInfo == null ) {
            return null;
        }

        Pagination pagination = new Pagination();

        pagination.setRequestedPage( paginationInfo.requestedPage() );
        pagination.setRequestedSize( paginationInfo.requestedSize() );
        pagination.setRetrievedResults( paginationInfo.retrievedResults() );
        pagination.setTotalResults( paginationInfo.totalResults() );
        if ( paginationInfo.nextPage() != null ) {
            pagination.setNextPage( String.valueOf( paginationInfo.nextPage() ) );
        }
        if ( paginationInfo.previousPage() != null ) {
            pagination.setPreviousPage( String.valueOf( paginationInfo.previousPage() ) );
        }

        return pagination;
    }
}
