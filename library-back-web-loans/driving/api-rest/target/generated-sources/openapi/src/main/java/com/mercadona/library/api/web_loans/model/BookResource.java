package com.mercadona.library.api.web_loans.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BookResource
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-11-25T15:18:29.628001Z[Europe/Lisbon]")
public class BookResource {

  private UUID id;

  private Long publicId;

  private String title;

  private String edition;

  private String coverUrl;

  private String epubUrl;

  private Boolean isDigital;

  private String languages;

  private BigDecimal price;

  @Valid
  private List<String> authors;

  private String publisher;

  public BookResource id(UUID id) {
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

  public BookResource publicId(Long publicId) {
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

  public BookResource title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  
  @Schema(name = "title", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BookResource edition(String edition) {
    this.edition = edition;
    return this;
  }

  /**
   * Get edition
   * @return edition
  */
  
  @Schema(name = "edition", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("edition")
  public String getEdition() {
    return edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public BookResource coverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
    return this;
  }

  /**
   * Get coverUrl
   * @return coverUrl
  */
  
  @Schema(name = "coverUrl", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("coverUrl")
  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

  public BookResource epubUrl(String epubUrl) {
    this.epubUrl = epubUrl;
    return this;
  }

  /**
   * Get epubUrl
   * @return epubUrl
  */
  
  @Schema(name = "epubUrl", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("epubUrl")
  public String getEpubUrl() {
    return epubUrl;
  }

  public void setEpubUrl(String epubUrl) {
    this.epubUrl = epubUrl;
  }

  public BookResource isDigital(Boolean isDigital) {
    this.isDigital = isDigital;
    return this;
  }

  /**
   * Get isDigital
   * @return isDigital
  */
  
  @Schema(name = "isDigital", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isDigital")
  public Boolean isIsDigital() {
    return isDigital;
  }

  public void setIsDigital(Boolean isDigital) {
    this.isDigital = isDigital;
  }

  public BookResource languages(String languages) {
    this.languages = languages;
    return this;
  }

  /**
   * Get languages
   * @return languages
  */
  
  @Schema(name = "languages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("languages")
  public String getLanguages() {
    return languages;
  }

  public void setLanguages(String languages) {
    this.languages = languages;
  }

  public BookResource price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @Valid 
  @Schema(name = "price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BookResource authors(List<String> authors) {
    this.authors = authors;
    return this;
  }

  public BookResource addAuthorsItem(String authorsItem) {
    if (this.authors == null) {
      this.authors = new ArrayList<>();
    }
    this.authors.add(authorsItem);
    return this;
  }

  /**
   * Get authors
   * @return authors
  */
  
  @Schema(name = "authors", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authors")
  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public BookResource publisher(String publisher) {
    this.publisher = publisher;
    return this;
  }

  /**
   * Get publisher
   * @return publisher
  */
  
  @Schema(name = "publisher", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("publisher")
  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookResource bookResource = (BookResource) o;
    return Objects.equals(this.id, bookResource.id) &&
        Objects.equals(this.publicId, bookResource.publicId) &&
        Objects.equals(this.title, bookResource.title) &&
        Objects.equals(this.edition, bookResource.edition) &&
        Objects.equals(this.coverUrl, bookResource.coverUrl) &&
        Objects.equals(this.epubUrl, bookResource.epubUrl) &&
        Objects.equals(this.isDigital, bookResource.isDigital) &&
        Objects.equals(this.languages, bookResource.languages) &&
        Objects.equals(this.price, bookResource.price) &&
        Objects.equals(this.authors, bookResource.authors) &&
        Objects.equals(this.publisher, bookResource.publisher);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, publicId, title, edition, coverUrl, epubUrl, isDigital, languages, price, authors, publisher);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookResource {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    publicId: ").append(toIndentedString(publicId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    edition: ").append(toIndentedString(edition)).append("\n");
    sb.append("    coverUrl: ").append(toIndentedString(coverUrl)).append("\n");
    sb.append("    epubUrl: ").append(toIndentedString(epubUrl)).append("\n");
    sb.append("    isDigital: ").append(toIndentedString(isDigital)).append("\n");
    sb.append("    languages: ").append(toIndentedString(languages)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    authors: ").append(toIndentedString(authors)).append("\n");
    sb.append("    publisher: ").append(toIndentedString(publisher)).append("\n");
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

