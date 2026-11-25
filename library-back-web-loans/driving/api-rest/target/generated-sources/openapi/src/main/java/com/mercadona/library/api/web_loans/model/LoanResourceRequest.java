package com.mercadona.library.api.web_loans.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * LoanResourceRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-11-25T15:18:29.628001Z[Europe/Lisbon]")
public class LoanResourceRequest {

  private UUID customerId;

  @Valid
  private List<UUID> bookIds = new ArrayList<>();

  /**
   * Default constructor
   * @deprecated Use {@link LoanResourceRequest#LoanResourceRequest(UUID, List<UUID>)}
   */
  @Deprecated
  public LoanResourceRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public LoanResourceRequest(UUID customerId, List<UUID> bookIds) {
    this.customerId = customerId;
    this.bookIds = bookIds;
  }

  public LoanResourceRequest customerId(UUID customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  */
  @NotNull @Valid 
  @Schema(name = "customerId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("customerId")
  public UUID getCustomerId() {
    return customerId;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }

  public LoanResourceRequest bookIds(List<UUID> bookIds) {
    this.bookIds = bookIds;
    return this;
  }

  public LoanResourceRequest addBookIdsItem(UUID bookIdsItem) {
    if (this.bookIds == null) {
      this.bookIds = new ArrayList<>();
    }
    this.bookIds.add(bookIdsItem);
    return this;
  }

  /**
   * Get bookIds
   * @return bookIds
  */
  @NotNull @Valid 
  @Schema(name = "bookIds", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("bookIds")
  public List<UUID> getBookIds() {
    return bookIds;
  }

  public void setBookIds(List<UUID> bookIds) {
    this.bookIds = bookIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoanResourceRequest loanResourceRequest = (LoanResourceRequest) o;
    return Objects.equals(this.customerId, loanResourceRequest.customerId) &&
        Objects.equals(this.bookIds, loanResourceRequest.bookIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, bookIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoanResourceRequest {\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    bookIds: ").append(toIndentedString(bookIds)).append("\n");
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

