package com.mercadona.library.loans.driven.clients.client;

import com.mercadona.library.loans.driven.clients.dto.BookResourceCollectionResponse;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BooksApiWebClient {
  private final WebClient booksWebClient;

  public BooksApiWebClient(
    WebClient.Builder webClientBuilder,
    @Value("${clients.books.base-url}") String baseUrl
  ) {
    this.booksWebClient = webClientBuilder.baseUrl(baseUrl).build();
  }

  public BookResourceCollectionResponse getBooks(Integer firstPage, Integer pageSize, String sort) {
    return booksWebClient
      .get()
      .uri(
        uriBuilder ->
          uriBuilder
            .path("/books")
            .queryParamIfPresent("firstPage", Optional.ofNullable(firstPage))
            .queryParamIfPresent("pageSize", Optional.ofNullable(pageSize))
            .queryParamIfPresent("sort", Optional.ofNullable(sort))
            .build())
      .retrieve()
      .bodyToMono(BookResourceCollectionResponse.class)
      .onErrorResume(ex -> {
        log.error("Error calling books API", ex);
        return Mono.error(ex);
      })
      .block();
  }
}
