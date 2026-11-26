package com.library.books.driven.repositories.models;

/** Type of event represented by a {@link BookOutboxEntity} row. */
public enum BookOutboxEventType {
  AVAILABLE,
  UNAVAILABLE,
  DELETED
}
