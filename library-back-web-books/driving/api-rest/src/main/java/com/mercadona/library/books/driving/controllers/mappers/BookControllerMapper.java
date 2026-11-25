package com.mercadona.library.books.driving.controllers.mappers;

import com.mercadona.library.api.web_books.model.AuthorResource;
import com.mercadona.library.api.web_books.model.BookResource;
import com.mercadona.library.api.web_books.model.BookResourceCollection;
import com.mercadona.library.api.web_books.model.BookResourceCollectionResponse;
import com.mercadona.library.api.web_books.model.BookResourceRequest;
import com.mercadona.library.api.web_books.model.BookResourceResponse;
import com.mercadona.library.api.web_books.model.PublisherResource;
import com.mercadona.library.books.domain.AuthorDTO;
import com.mercadona.library.books.domain.BookDTO;
import com.mercadona.library.books.domain.PublisherDTO;
import com.mercadona.library.books.application.utils.pagination.PagedResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BookControllerMapper {

  @Mapping(target = "pagination", source = "pagination")
  @Mapping(target = "data", source = "data", qualifiedByName = "toBookResourceCollection")
  BookResourceCollectionResponse toBookResourceCollectionResponse(PagedResult<BookDTO> page);

  @Named("toBookResourceCollection")
  default BookResourceCollection toBookResourceCollection(List<BookDTO> items) {
    if (CollectionUtils.isEmpty(items)) return new BookResourceCollection().books(List.of());

    return new BookResourceCollection()
      .books(items.stream().map(this::toBookResource).toList());
  }

  BookResource toBookResource(BookDTO dto);

  AuthorResource toAuthorResource(AuthorDTO dto);

  PublisherResource toPublisherResource(PublisherDTO dto);

  default BookResourceResponse toBookResourceResponse(BookDTO dto) {
    return new BookResourceResponse().data(toBookResource(dto));
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "publisher", expression = "java(toPublisherReference(request.getPublisherId()))")
  @Mapping(target = "authors", expression = "java(toAuthorReferences(request.getAuthorId()))")
  BookDTO toBookDTO(BookResourceRequest request);

  default PublisherDTO toPublisherReference(UUID publisherId) {
    return publisherId == null ? null : new PublisherDTO(publisherId, null, null, null);
  }

  default Set<AuthorDTO> toAuthorReferences(UUID authorId) {
    if (authorId == null) return null;

    return Set.of(new AuthorDTO(authorId, null, null, null, null));
  }
}
