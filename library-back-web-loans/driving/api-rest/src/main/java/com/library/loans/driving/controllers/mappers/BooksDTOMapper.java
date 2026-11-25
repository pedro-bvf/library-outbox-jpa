package com.library.loans.driving.controllers.mappers;

import com.library.api.web_loans.model.BookCollection;
import com.library.api.web_loans.model.BookCollectionResponse;
import com.library.api.web_loans.model.BookResource;
import com.library.loans.application.utils.pagination.PagedResult;
import com.library.loans.domain.BookCatalogEntry;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

@Mapper(componentModel = "spring")
public interface BooksDTOMapper {

  @Mapping(target = "data", source = "data", qualifiedByName = "toBookCollection")
  @Mapping(target = "pagination", source = "pagination")
  BookCollectionResponse toBookCollectionResponse(PagedResult<BookCatalogEntry> page);

  BookResource toBookResource(BookCatalogEntry entry);

  @Named("toBookCollection")
  default BookCollection toBookCollection(List<BookCatalogEntry> items) {
    if (CollectionUtils.isEmpty(items)) {
      return new BookCollection().books(List.of());
    }
    return new BookCollection().books(items.stream().map(this::toBookResource).toList());
  }
}
