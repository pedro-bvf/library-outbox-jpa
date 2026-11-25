package com.library.loans.domain;

import java.util.UUID;

public record Book(
    UUID id,
    Long publicId
) {
}
