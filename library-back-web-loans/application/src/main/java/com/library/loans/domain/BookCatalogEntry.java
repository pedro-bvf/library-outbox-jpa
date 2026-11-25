package com.library.loans.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record BookCatalogEntry(
    UUID id,
    Long publicId,
    String title,
    String edition,
    String coverUrl,
    String epubUrl,
    Boolean isDigital,
    String languages,
    BigDecimal price,
    List<String> authors,
    String publisher
) {
}
