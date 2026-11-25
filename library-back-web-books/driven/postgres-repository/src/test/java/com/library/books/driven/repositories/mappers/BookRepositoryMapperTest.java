package com.library.books.driven.repositories.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.books.domain.BookDTO;
import com.library.books.driven.repositories.models.AuthorMO;
import com.library.books.driven.repositories.models.BookMO;
import com.library.books.driven.repositories.models.PublisherMO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class BookRepositoryMapperTest {

  private final BookRepositoryMapper mapper = Mappers.getMapper(BookRepositoryMapper.class);

  @Test
  void toBookDTO_nullInput_returnsNull() {
    assertThat(mapper.toBookDTO(null)).isNull();
  }

  @Test
  void toBookDTO_mapsAllFieldsIncludingNestedPublisherAndAuthors() {
    var publisher = new PublisherMO();
    publisher.setId(UUID.randomUUID());
    publisher.setPublicId(1L);
    publisher.setName("Editorial Alpha");
    publisher.setAddress("Av. Central 123");

    var author = new AuthorMO();
    author.setId(UUID.randomUUID());
    author.setPublicId(2L);
    author.setName("Gabriel García Márquez");
    author.setBirthday(LocalDate.of(1927, 3, 6));
    author.setUrlImage("https://img/garciamarquez.jpg");

    var bookMO = new BookMO();
    bookMO.setId(UUID.randomUUID());
    bookMO.setPublicId(100L);
    bookMO.setTitle("Cien años de soledad");
    bookMO.setCoverUrl("https://img/soledad.jpg");
    bookMO.setEdition("1st");
    bookMO.setPrice(BigDecimal.valueOf(19.99));
    bookMO.setLanguages("ES");
    bookMO.setEpubUrl("https://epub/soledad");
    bookMO.setIsDigital(true);
    bookMO.setIsDeleted(false);
    bookMO.setPublisher(publisher);
    bookMO.setAuthors(Set.of(author));

    var result = mapper.toBookDTO(bookMO);

    assertThat(result.id()).isEqualTo(bookMO.getId());
    assertThat(result.publicId()).isEqualTo(100L);
    assertThat(result.title()).isEqualTo("Cien años de soledad");
    assertThat(result.coverUrl()).isEqualTo("https://img/soledad.jpg");
    assertThat(result.edition()).isEqualTo("1st");
    assertThat(result.price()).isEqualByComparingTo(BigDecimal.valueOf(19.99));
    assertThat(result.languages()).isEqualTo("ES");
    assertThat(result.epubUrl()).isEqualTo("https://epub/soledad");
    assertThat(result.isDigital()).isTrue();
    assertThat(result.isDeleted()).isFalse();

    assertThat(result.publisher().id()).isEqualTo(publisher.getId());
    assertThat(result.publisher().name()).isEqualTo("Editorial Alpha");

    assertThat(result.authors()).hasSize(1);
    var mappedAuthor = result.authors().iterator().next();
    assertThat(mappedAuthor.id()).isEqualTo(author.getId());
    assertThat(mappedAuthor.name()).isEqualTo("Gabriel García Márquez");
  }

  @Test
  void updateBookMOFromDTO_overwritesOnlyNonNullFields() {
    var publisher = new PublisherMO();
    publisher.setId(UUID.randomUUID());
    var existingId = UUID.randomUUID();

    var existing = new BookMO();
    existing.setId(existingId);
    existing.setTitle("Original title");
    existing.setEdition("Original edition");
    existing.setPrice(BigDecimal.TEN);
    existing.setPublisher(publisher);
    existing.setAuthors(Set.of());

    var changes = new BookDTO(
      UUID.randomUUID(), null, "New title", null, null, null, null, null, null, null, null, null);

    mapper.updateBookMOFromDTO(changes, existing);

    assertThat(existing.getTitle()).isEqualTo("New title");
    assertThat(existing.getEdition()).isEqualTo("Original edition");
    assertThat(existing.getPrice()).isEqualByComparingTo(BigDecimal.TEN);
    assertThat(existing.getId()).isEqualTo(existingId);
    assertThat(existing.getPublisher()).isSameAs(publisher);
  }

  @Test
  void toBookMO_mapsPlainFieldsAndIgnoresIdPublisherAndAuthors() {
    var book = new BookDTO(
      UUID.randomUUID(), 100L, "Title", "cover", "1st", BigDecimal.valueOf(19.99), "ES", "epub",
      true, false, null, null);

    var result = mapper.toBookMO(book);

    assertThat(result.getId()).isNull();
    assertThat(result.getPublicId()).isEqualTo(100L);
    assertThat(result.getTitle()).isEqualTo("Title");
    assertThat(result.getCoverUrl()).isEqualTo("cover");
    assertThat(result.getEdition()).isEqualTo("1st");
    assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(19.99));
    assertThat(result.getLanguages()).isEqualTo("ES");
    assertThat(result.getEpubUrl()).isEqualTo("epub");
    assertThat(result.getIsDigital()).isTrue();
    assertThat(result.getIsDeleted()).isFalse();
    assertThat(result.getPublisher()).isNull();
    assertThat(result.getAuthors()).isNull();
  }
}
