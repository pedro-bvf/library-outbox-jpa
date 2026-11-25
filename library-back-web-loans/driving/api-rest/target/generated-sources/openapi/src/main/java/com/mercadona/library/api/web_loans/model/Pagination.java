package com.mercadona.library.api.web_loans.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Pagination
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-11-25T15:18:29.628001Z[Europe/Lisbon]")
public class Pagination {

  private Integer requestedPage;

  private Integer requestedSize;

  private Integer retrievedResults;

  private Long totalResults;

  private String nextPage;

  private String previousPage;

  public Pagination requestedPage(Integer requestedPage) {
    this.requestedPage = requestedPage;
    return this;
  }

  /**
   * Get requestedPage
   * @return requestedPage
  */
  
  @Schema(name = "requestedPage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requestedPage")
  public Integer getRequestedPage() {
    return requestedPage;
  }

  public void setRequestedPage(Integer requestedPage) {
    this.requestedPage = requestedPage;
  }

  public Pagination requestedSize(Integer requestedSize) {
    this.requestedSize = requestedSize;
    return this;
  }

  /**
   * Get requestedSize
   * @return requestedSize
  */
  
  @Schema(name = "requestedSize", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requestedSize")
  public Integer getRequestedSize() {
    return requestedSize;
  }

  public void setRequestedSize(Integer requestedSize) {
    this.requestedSize = requestedSize;
  }

  public Pagination retrievedResults(Integer retrievedResults) {
    this.retrievedResults = retrievedResults;
    return this;
  }

  /**
   * Get retrievedResults
   * @return retrievedResults
  */
  
  @Schema(name = "retrievedResults", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("retrievedResults")
  public Integer getRetrievedResults() {
    return retrievedResults;
  }

  public void setRetrievedResults(Integer retrievedResults) {
    this.retrievedResults = retrievedResults;
  }

  public Pagination totalResults(Long totalResults) {
    this.totalResults = totalResults;
    return this;
  }

  /**
   * Get totalResults
   * @return totalResults
  */
  
  @Schema(name = "totalResults", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalResults")
  public Long getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(Long totalResults) {
    this.totalResults = totalResults;
  }

  public Pagination nextPage(String nextPage) {
    this.nextPage = nextPage;
    return this;
  }

  /**
   * Get nextPage
   * @return nextPage
  */
  
  @Schema(name = "nextPage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nextPage")
  public String getNextPage() {
    return nextPage;
  }

  public void setNextPage(String nextPage) {
    this.nextPage = nextPage;
  }

  public Pagination previousPage(String previousPage) {
    this.previousPage = previousPage;
    return this;
  }

  /**
   * Get previousPage
   * @return previousPage
  */
  
  @Schema(name = "previousPage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("previousPage")
  public String getPreviousPage() {
    return previousPage;
  }

  public void setPreviousPage(String previousPage) {
    this.previousPage = previousPage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pagination pagination = (Pagination) o;
    return Objects.equals(this.requestedPage, pagination.requestedPage) &&
        Objects.equals(this.requestedSize, pagination.requestedSize) &&
        Objects.equals(this.retrievedResults, pagination.retrievedResults) &&
        Objects.equals(this.totalResults, pagination.totalResults) &&
        Objects.equals(this.nextPage, pagination.nextPage) &&
        Objects.equals(this.previousPage, pagination.previousPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestedPage, requestedSize, retrievedResults, totalResults, nextPage, previousPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pagination {\n");
    sb.append("    requestedPage: ").append(toIndentedString(requestedPage)).append("\n");
    sb.append("    requestedSize: ").append(toIndentedString(requestedSize)).append("\n");
    sb.append("    retrievedResults: ").append(toIndentedString(retrievedResults)).append("\n");
    sb.append("    totalResults: ").append(toIndentedString(totalResults)).append("\n");
    sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
    sb.append("    previousPage: ").append(toIndentedString(previousPage)).append("\n");
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

