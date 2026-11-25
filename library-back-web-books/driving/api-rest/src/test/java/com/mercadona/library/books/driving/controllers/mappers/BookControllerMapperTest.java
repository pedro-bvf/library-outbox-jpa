package com.mercadona.library.books.driving.controllers.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import com.mercadona.library.api.web_books.model.BookResourceRequest;
import com.mercadona.library.books.domain.AuthorDTO;
import com.mercadona.library.books.domain.BookDTO;
import com.mercadona.library.books.domain.PublisherDTO;
import com.mercadona.library.books.application.utils.pagination.PagedResult;
import com.mercadona.library.books.application.utils.pagination.PaginationInfo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class BookControllerMapperTest {

  private final BookControllerMapper mapper = Mappers.getMapper(BookControllerMapper.class);

  @Test
  void toBookResource_mapsAllFieldsAndConvertsPriceToFloat() {
    var publisher =
      new PublisherDTO(UUID.randomUUID(), 1L, "Editorial Alpha", "Av. Central 123");
    var author = new AuthorDTO(
      UUID.randomUUID(), 2L, "Gabriel García Márquez", LocalDate.of(1927, 3, 6),
      "https://img/gm.jpg");
    var book = new BookDTO(
      UUID.randomUUID(), 100L, "Cien años de soledad", "https://img/soledad.jpg", "1st",
      BigDecimal.valueOf(19.99), "ES", "https://epub/soledad", true, false, publisher,
      Set.of(author));

    var result = mapper.toBookResource(book);

    assertThat(result.getId()).isEqualTo(book.id());
    assertThat(result.getPublicId()).isEqualTo(100L);
    assertThat(result.getTitle()).isEqualTo("Cien años de soledad");
    assertThat(result.getPrice()).isEqualTo(19.99f);
    assertThat(result.isIsDigital()).isTrue();
    assertThat(result.isIsDeleted()).isFalse();
    assertThat(result.getPublisher().getName()).isEqualTo("Editorial Alpha");
    assertThat(result.getAuthors()).hasSize(1);
    assertThat(result.getAuthors().get(0).getName()).isEqualTo("Gabriel García Márquez");
  }

  @Test
  void toBookResource_nullPrice_leavesPriceNull() {
    var book = emptyBookDTO();

    var result = mapper.toBookResource(book);

    assertThat(result.getPrice()).isNull();
  }

  @Test
  void toBookResourceCollection_emptyList_returnsResourceWithEmptyBooksList() {
    var result = mapper.toBookResourceCollection(List.of());

    assertThat(result.getBooks()).isEmpty();
  }

  @Test
  void toBookResourceCollection_nullList_returnsResourceWithEmptyBooksList() {
    var result = mapper.toBookResourceCollection(null);

    assertThat(result.getBooks()).isEmpty();
  }

  @Test
  void toBookResourceCollection_populatedList_mapsEachBook() {
    var book = emptyBookDTO();

    var result = mapper.toBookResourceCollection(List.of(book));

    assertThat(result.getBooks()).hasSize(1);
    assertThat(result.getBooks().get(0).getId()).isEqualTo(book.id());
  }

  @Test
  void toBookResourceResponse_wrapsBookResourceInData() {
    var book = emptyBookDTO();

    var result = mapper.toBookResourceResponse(book);

    assertThat(result.getData().getId()).isEqualTo(book.id());
  }

  @Test
  void toBookResourceCollectionResponse_mapsDataAndPaginationConvertingPagesToStrings() {
    var book = emptyBookDTO();
    var pagination = new PaginationInfo(2, 10, 1, 11, 2, null, 1);
    var pagedResult = new PagedResult<>(List.of(book), pagination);

    var result = mapper.toBookResourceCollectionResponse(pagedResult);

    assertThat(result.getData().getBooks()).hasSize(1);
    assertThat(result.getPagination().getRequestedPage()).isEqualTo(2);
    assertThat(result.getPagination().getNextPage()).isNull();
    assertThat(result.getPagination().getPreviousPage()).isEqualTo("1");
  }

  @Test
  void toBookDTO_mapsFieldsConvertsPriceAndBuildsPublisherAndAuthorReferences() {
    var publisherId = UUID.randomUUID();
    var authorId = UUID.randomUUID();
    var request = new BookResourceRequest()
      .publicId(5L)
      .title("New Title")
      .coverUrl("cover")
      .edition("2nd")
      .price(9.99f)
      .languages("EN")
      .epubUrl("epub")
      .isDigital(true)
      .isDeleted(false)
      .publisherId(publisherId)
      .authorId(authorId);

    var result = mapper.toBookDTO(request);

    assertThat(result.id()).isNull();
    assertThat(result.publicId()).isEqualTo(5L);
    assertThat(result.title()).isEqualTo("New Title");
    assertThat(result.price()).isEqualByComparingTo(BigDecimal.valueOf(9.99f));
    assertThat(result.isDigital()).isTrue();
    assertThat(result.publisher().id()).isEqualTo(publisherId);
    assertThat(result.authors()).extracting(AuthorDTO::id).containsExactly(authorId);
  }

  @Test
  void toBookDTO_nullPublisherIdAndEmptyAuthors_mapsToNullReferences() {
    var request = new BookResourceRequest().title("New Title");

    var result = mapper.toBookDTO(request);

    assertThat(result.publisher()).isNull();
    assertThat(result.authors()).isNull();
  }

  private BookDTO emptyBookDTO() {
    return new BookDTO(
      UUID.randomUUID(), 1L, "Title", null, null, null, null, null, null, null, null, null);
  }
}
