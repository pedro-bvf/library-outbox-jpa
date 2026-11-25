package com.library.availability.application.ports.driven;

import com.library.availability.domain.BookDTO;

public interface BookRepositoryPort {
  void save(BookDTO book);

  void deleteById(Long publicId);

}
