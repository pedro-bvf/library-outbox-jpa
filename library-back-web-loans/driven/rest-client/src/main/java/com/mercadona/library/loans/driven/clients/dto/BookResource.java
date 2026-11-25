package com.mercadona.library.loans.driven.clients.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record BookResource(
    UUID id,
    Long publicId,
    String title,
    String edition,
    String coverUrl,
    String epubUrl,
    Boolean isDigital,
    String languages,
    BigDecimal price,
    List<AuthorResource> authors,
    PublisherResource publisher
) {
}
