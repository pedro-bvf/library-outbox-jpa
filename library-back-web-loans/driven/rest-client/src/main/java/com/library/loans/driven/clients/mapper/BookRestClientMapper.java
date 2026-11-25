package com.library.loans.driven.clients.mapper;

import com.library.loans.application.utils.pagination.PagedResult;
import com.library.loans.application.utils.pagination.PaginationInfo;
import com.library.loans.domain.BookCatalogEntry;
import com.library.loans.driven.clients.dto.AuthorResource;
import com.library.loans.driven.clients.dto.BookResource;
import com.library.loans.driven.clients.dto.BookResourceCollection;
import com.library.loans.driven.clients.dto.BookResourceCollectionResponse;
import com.library.loans.driven.clients.dto.Pagination;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

@Mapper(componentModel = "spring")
public interface BookRestClientMapper {

  @Mapping(target = "authors", source = "authors", qualifiedByName = "toAuthorNames")
  @Mapping(target = "publisher", source = "publisher.name")
  BookCatalogEntry toDomain(BookResource resource);

  @Named("toAuthorNames")
  default List<String> toAuthorNames(List<AuthorResource> authors) {
    if (CollectionUtils.isEmpty(authors)) {
      return List.of();
    }
    return authors.stream().map(AuthorResource::name).toList();
  }

  default List<BookCatalogEntry> toDomain(BookResourceCollection collection) {
    if (collection == null || CollectionUtils.isEmpty(collection.books())) {
      return List.of();
    }
    return collection.books().stream().map(this::toDomain).toList();
  }

  PagedResult<BookCatalogEntry> toDomain(BookResourceCollectionResponse response);

  @Mapping(target = "totalPages", constant = "0")
  PaginationInfo toDomain(Pagination pagination);
}
