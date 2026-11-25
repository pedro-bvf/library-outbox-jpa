package com.mercadona.library.loans.domain;

import java.time.LocalDate;
import java.util.List;

public record Author(
    Long id,
    String name,
    LocalDate birthday,
    String urlImage,
    List<Book> books
) {
}
