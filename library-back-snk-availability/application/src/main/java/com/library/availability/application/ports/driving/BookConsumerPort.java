package com.library.availability.application.ports.driving;

import com.library.availability.domain.BookDTO;

public interface BookConsumerPort {
  void process(BookDTO book);

  void delete(Long publicId);
}
