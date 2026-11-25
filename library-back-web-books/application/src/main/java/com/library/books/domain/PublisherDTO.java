package com.library.books.domain;

import java.util.UUID;

public record PublisherDTO(
  UUID id,
  Long publicId,
  String name,
  String address
) {
}
