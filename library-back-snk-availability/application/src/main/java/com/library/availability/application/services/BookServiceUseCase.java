package com.library.availability.application.services;

import com.library.availability.application.ports.driven.BookRepositoryPort;
import com.library.availability.application.ports.driving.BookConsumerPort;
import com.library.availability.domain.BookDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceUseCase implements BookConsumerPort {
  private final BookRepositoryPort bookRepositoryPort;

  @Transactional
  @Override
  public void process(BookDTO book) {
    bookRepositoryPort.save(book);
  }

  @Transactional
  @Override
  public void delete(Long publicId) {
    bookRepositoryPort.deleteById(publicId);
  }
}
