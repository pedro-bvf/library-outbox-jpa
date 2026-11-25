package com.mercadona.library.books.driven.repositories.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "book")
public class BookMO {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "public_id")
  private Long publicId;

  private String title;

  @Column(name = "cover_url")
  private String coverUrl;

  private String edition;

  private BigDecimal price;

  private String languages;

  @Column(name = "epub_url")
  private String epubUrl;

  @Column(name = "is_digital")
  private Boolean isDigital;

  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "publisher_id")
  private PublisherMO publisher;

  @ManyToMany
  @JoinTable(
    name = "author_book",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
  private Set<AuthorMO> authors;
}
