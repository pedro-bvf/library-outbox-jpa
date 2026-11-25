package com.mercadona.library.books.driven.repositories.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "publisher")
public class PublisherMO {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "public_id")
  private Long publicId;

  private String name;

  private String address;

  @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
  private Set<BookMO> books;
}
