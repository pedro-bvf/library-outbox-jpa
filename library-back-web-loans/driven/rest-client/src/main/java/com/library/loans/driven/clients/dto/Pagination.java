package com.library.loans.driven.clients.dto;

public record Pagination(
    Integer requestedPage,
    Integer requestedSize,
    Integer retrievedResults,
    Long totalResults,
    String nextPage,
    String previousPage
) {
}
