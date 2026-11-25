package com.library.loans.domain;

import java.time.LocalDate;
import java.util.UUID;

public record Customer(
    UUID id,
    Long publicId,
    String name,
    String address,
    Long phone,
    LocalDate birthday,
    String cardImageUrl
) {
}
