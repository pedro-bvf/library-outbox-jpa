package com.library.books.application.ports.driven;

import com.library.books.domain.BookDTO;

public interface BookOutboxPublisherPort {
  void saveBookOutbox(BookDTO book);

  void deleteBookOutbox(BookDTO book);
}
