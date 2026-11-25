package com.mercadona.library.books.application.ports.driven;

import com.mercadona.library.books.domain.BookDTO;
import com.mercadona.library.books.application.utils.pagination.PagedResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepositoryPort {
  PagedResult<BookDTO> findAllBooks(int pageNumber, int pageSize, String sort);

  Optional<BookDTO> findBookById(UUID id);

  BookDTO createBook(BookDTO book);

  Optional<BookDTO> updateBook(UUID id, BookDTO changes);

  void deleteBookById(UUID id);

  List<BookDTO> findByPublicIds(List<Long> publicIds);
}
