package com.mercadona.library.api.web_loans.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.mercadona.library.api.web_loans.model.CustomerResource;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LoanResource
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-11-25T15:18:29.628001Z[Europe/Lisbon]")
public class LoanResource {

  private UUID id;

  private Long publicId;

  private CustomerResource customer;

  @Valid
  private List<UUID> books;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime bookingDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate bookingExpirationDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime returnedDate;

  public LoanResource id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LoanResource publicId(Long publicId) {
    this.publicId = publicId;
    return this;
  }

  /**
   * Get publicId
   * @return publicId
  */
  
  @Schema(name = "publicId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("publicId")
  public Long getPublicId() {
    return publicId;
  }

  public void setPublicId(Long publicId) {
    this.publicId = publicId;
  }

  public LoanResource customer(CustomerResource customer) {
    this.customer = customer;
    return this;
  }

  /**
   * Get customer
   * @return customer
  */
  @Valid 
  @Schema(name = "customer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("customer")
  public CustomerResource getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerResource customer) {
    this.customer = customer;
  }

  public LoanResource books(List<UUID> books) {
    this.books = books;
    return this;
  }

  public LoanResource addBooksItem(UUID booksItem) {
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
  public List<UUID> getBooks() {
    return books;
  }

  public void setBooks(List<UUID> books) {
    this.books = books;
  }

  public LoanResource bookingDate(OffsetDateTime bookingDate) {
    this.bookingDate = bookingDate;
    return this;
  }

  /**
   * Get bookingDate
   * @return bookingDate
  */
  @Valid 
  @Schema(name = "bookingDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bookingDate")
  public OffsetDateTime getBookingDate() {
    return bookingDate;
  }

  public void setBookingDate(OffsetDateTime bookingDate) {
    this.bookingDate = bookingDate;
  }

  public LoanResource bookingExpirationDate(LocalDate bookingExpirationDate) {
    this.bookingExpirationDate = bookingExpirationDate;
    return this;
  }

  /**
   * Get bookingExpirationDate
   * @return bookingExpirationDate
  */
  @Valid 
  @Schema(name = "bookingExpirationDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bookingExpirationDate")
  public LocalDate getBookingExpirationDate() {
    return bookingExpirationDate;
  }

  public void setBookingExpirationDate(LocalDate bookingExpirationDate) {
    this.bookingExpirationDate = bookingExpirationDate;
  }

  public LoanResource returnedDate(OffsetDateTime returnedDate) {
    this.returnedDate = returnedDate;
    return this;
  }

  /**
   * Get returnedDate
   * @return returnedDate
  */
  @Valid 
  @Schema(name = "returnedDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("returnedDate")
  public OffsetDateTime getReturnedDate() {
    return returnedDate;
  }

  public void setReturnedDate(OffsetDateTime returnedDate) {
    this.returnedDate = returnedDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoanResource loanResource = (LoanResource) o;
    return Objects.equals(this.id, loanResource.id) &&
        Objects.equals(this.publicId, loanResource.publicId) &&
        Objects.equals(this.customer, loanResource.customer) &&
        Objects.equals(this.books, loanResource.books) &&
        Objects.equals(this.bookingDate, loanResource.bookingDate) &&
        Objects.equals(this.bookingExpirationDate, loanResource.bookingExpirationDate) &&
        Objects.equals(this.returnedDate, loanResource.returnedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, publicId, customer, books, bookingDate, bookingExpirationDate, returnedDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoanResource {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    publicId: ").append(toIndentedString(publicId)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    books: ").append(toIndentedString(books)).append("\n");
    sb.append("    bookingDate: ").append(toIndentedString(bookingDate)).append("\n");
    sb.append("    bookingExpirationDate: ").append(toIndentedString(bookingExpirationDate)).append("\n");
    sb.append("    returnedDate: ").append(toIndentedString(returnedDate)).append("\n");
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

