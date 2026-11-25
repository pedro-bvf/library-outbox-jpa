package com.mercadona.library.availability.application.ports.driven;

import com.mercadona.library.availability.domain.BookDTO;

public interface BookRepositoryPort {
  void save(BookDTO book);

  void deleteById(Long publicId);

}
