package com.mercadona.library.api.web_loans.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
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
 * CustomerResource
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-11-25T15:18:29.628001Z[Europe/Lisbon]")
public class CustomerResource {

  private UUID id;

  private Long publicId;

  private String name;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthday;

  private String address;

  private Integer phone;

  private String cardImageUrl;

  public CustomerResource id(UUID id) {
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

  public CustomerResource publicId(Long publicId) {
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

  public CustomerResource name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CustomerResource birthday(LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }

  /**
   * Get birthday
   * @return birthday
  */
  @Valid 
  @Schema(name = "birthday", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("birthday")
  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public CustomerResource address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  
  @Schema(name = "address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public CustomerResource phone(Integer phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
  */
  
  @Schema(name = "phone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phone")
  public Integer getPhone() {
    return phone;
  }

  public void setPhone(Integer phone) {
    this.phone = phone;
  }

  public CustomerResource cardImageUrl(String cardImageUrl) {
    this.cardImageUrl = cardImageUrl;
    return this;
  }

  /**
   * Get cardImageUrl
   * @return cardImageUrl
  */
  
  @Schema(name = "cardImageUrl", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cardImageUrl")
  public String getCardImageUrl() {
    return cardImageUrl;
  }

  public void setCardImageUrl(String cardImageUrl) {
    this.cardImageUrl = cardImageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerResource customerResource = (CustomerResource) o;
    return Objects.equals(this.id, customerResource.id) &&
        Objects.equals(this.publicId, customerResource.publicId) &&
        Objects.equals(this.name, customerResource.name) &&
        Objects.equals(this.birthday, customerResource.birthday) &&
        Objects.equals(this.address, customerResource.address) &&
        Objects.equals(this.phone, customerResource.phone) &&
        Objects.equals(this.cardImageUrl, customerResource.cardImageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, publicId, name, birthday, address, phone, cardImageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerResource {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    publicId: ").append(toIndentedString(publicId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    cardImageUrl: ").append(toIndentedString(cardImageUrl)).append("\n");
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

