package com.mercadona.library.books.driven.repositories.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "author")
public class AuthorMO {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "public_id")
  private Long publicId;

  private String name;

  private LocalDate birthday;

  @Column(name = "url_image")
  private String urlImage;

  @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
  private Set<BookMO> books;
}
