package com.library.loans.driven.clients.dto;

import java.util.UUID;

public record AuthorResource(UUID id, Long publicId, String name) {
}
