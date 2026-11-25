package com.mercadona.library.availability.repositories.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@Table(name = "book")
public class BookMO {
  @Id
  @UuidGenerator
  @Column(name = "id")
  private UUID id;

  @Column(name = "public_id")
  private Long publicId;

  @Column(name = "available", nullable = false)
  private boolean available;
}
