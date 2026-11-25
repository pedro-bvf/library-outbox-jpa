package com.mercadona.library.books.application.ports.driving;

import com.mercadona.library.books.domain.BookDTO;
import com.mercadona.library.books.application.utils.pagination.PagedResult;

import java.util.List;
import java.util.UUID;

public interface BookServicePort {
  PagedResult<BookDTO> getAllBooks(int pageNumber, int pageSize, String sort);

  BookDTO getBook(UUID id);

  BookDTO saveBook(BookDTO book);

  BookDTO updateBook(UUID id, BookDTO book);

  void deleteBook(UUID id);

  List<BookDTO> searchBooksByPublicIds(List<Long> publicIds);
}
