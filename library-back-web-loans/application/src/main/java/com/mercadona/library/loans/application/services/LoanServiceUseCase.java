package com.mercadona.library.loans.application.services;

import com.github.f4b6a3.tsid.TsidCreator;
import com.mercadona.library.loans.application.exceptions.EntityNotFoundException;
import com.mercadona.library.loans.application.ports.driven.BookRepositoryPort;
import com.mercadona.library.loans.application.ports.driven.CustomerRepositoryPort;
import com.mercadona.library.loans.application.ports.driven.LoanRepositoryPort;
import com.mercadona.library.loans.application.ports.driving.LoanServicePort;
import com.mercadona.library.loans.domain.Book;
import com.mercadona.library.loans.domain.Loan;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class LoanServiceUseCase implements LoanServicePort {

  private final LoanRepositoryPort loanRepository;
  private final CustomerRepositoryPort customerRepository;
  private final BookRepositoryPort bookRepository;

  @Override
  @Transactional(readOnly = true)
  public Loan getLoanById(UUID id) {
    return loanRepository
        .findById(id)
        .orElseThrow(() -> EntityNotFoundException.of("Loan with id %s does not exist.", id));
  }

  @Override
  @Transactional
  public Loan createLoan(Loan loan) {
    var customerId = loan.customer().id();
    if (!customerRepository.existsById(customerId)) {
      throw EntityNotFoundException.of("Customer with id %s does not exist.", customerId);
    }

    var bookIds = loan.books().stream().map(Book::id).toList();
    var existingIds = bookRepository.findExistingIds(bookIds);

    if (existingIds.size() != bookIds.size()) {
      var missingIds = bookIds.stream().filter(id -> !existingIds.contains(id)).toList();
      throw EntityNotFoundException.of("Some books do not exist: %s.", missingIds);
    }

    OffsetDateTime now = OffsetDateTime.now();
    var bookedLoan =
        loan.withBooking(TsidCreator.getTsid().toLong(), now, now.plusDays(14).toLocalDate());

    return loanRepository.save(bookedLoan);
  }

  @Override
  @Transactional
  public Loan returnLoan(UUID id) {
    var loan =
        loanRepository
            .findById(id)
            .orElseThrow(() -> EntityNotFoundException.of("Loan with id %s does not exist.", id));
    return loanRepository.save(loan.withReturnedDate(OffsetDateTime.now()));
  }

  @Override
  @Transactional
  public void cancelLoan(UUID id) {
    loanRepository.deleteById(id);
  }
}
