package com.mercadona.library.availability.application.ports.driving;

import com.mercadona.library.availability.domain.BookDTO;

public interface BookConsumerPort {
  void process(BookDTO book);

  void delete(Long publicId);
}
