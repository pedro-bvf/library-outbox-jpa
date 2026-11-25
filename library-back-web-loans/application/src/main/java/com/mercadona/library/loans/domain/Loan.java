package com.mercadona.library.loans.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record Loan(
    UUID id,
    Long publicId,
    Customer customer,
    OffsetDateTime bookingDate,
    LocalDate bookingExpirationDate,
    OffsetDateTime returnedDate,
    List<Book> books
) {
  public Loan withBooking(Long publicId, OffsetDateTime bookingDate, LocalDate bookingExpirationDate) {
    return new Loan(id, publicId, customer, bookingDate, bookingExpirationDate, returnedDate, books);
  }

  public Loan withReturnedDate(OffsetDateTime returnedDate) {
    return new Loan(id, publicId, customer, bookingDate, bookingExpirationDate, returnedDate, books);
  }
}
