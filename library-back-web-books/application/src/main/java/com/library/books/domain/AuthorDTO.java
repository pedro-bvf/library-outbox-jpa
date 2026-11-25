package com.library.books.domain;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
  UUID id,
  Long publicId,
  String name,
  LocalDate birthday,
  String urlImage
) {
}
