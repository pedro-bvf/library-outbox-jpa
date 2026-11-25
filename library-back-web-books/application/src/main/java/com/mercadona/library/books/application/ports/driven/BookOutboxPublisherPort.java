package com.mercadona.library.books.application.ports.driven;

import com.mercadona.library.books.domain.BookDTO;

public interface BookOutboxPublisherPort {
  void saveBookOutbox(BookDTO book);

  void deleteBookOutbox(BookDTO book);
}
