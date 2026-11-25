package com.mercadona.library.books.domain;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record BookDTO(
  UUID id,
  Long publicId,
  String title,
  String coverUrl,
  String edition,
  BigDecimal price,
  String languages,
  String epubUrl,
  Boolean isDigital,
  Boolean isDeleted,
  PublisherDTO publisher,
  Set<AuthorDTO> authors
) {
  public BookDTO withResolvedPublisherAndAuthors(PublisherDTO resolvedPublisher, Set<AuthorDTO> resolvedAuthors) {
    return new BookDTO(
      id, publicId, title, coverUrl, edition, price, languages, epubUrl, isDigital, isDeleted,
      resolvedPublisher, resolvedAuthors);
  }
}
