package com.mercadona.library.availability.domain;

public record BookDTO(
  Long publicId,
  boolean available
) {
}
