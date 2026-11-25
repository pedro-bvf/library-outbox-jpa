package com.mercadona.library.api.web_loans.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.mercadona.library.api.web_loans.model.BookResource;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BookCollection
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-11-25T15:18:29.628001Z[Europe/Lisbon]")
public class BookCollection {

  @Valid
  private List<@Valid BookResource> books;

  public BookCollection books(List<@Valid BookResource> books) {
    this.books = books;
    return this;
  }

  public BookCollection addBooksItem(BookResource booksItem) {
    if (this.books == null) {
      this.books = new ArrayList<>();
    }
    this.books.add(booksItem);
    return this;
  }

  /**
   * Get books
   * @return books
  */
  @Valid 
  @Schema(name = "books", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("books")
  public List<@Valid BookResource> getBooks() {
    return books;
  }

  public void setBooks(List<@Valid BookResource> books) {
    this.books = books;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookCollection bookCollection = (BookCollection) o;
    return Objects.equals(this.books, bookCollection.books);
  }

  @Override
  public int hashCode() {
    return Objects.hash(books);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookCollection {\n");
    sb.append("    books: ").append(toIndentedString(books)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

