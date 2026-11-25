package com.mercadona.library.loans.application.ports.driven;

import com.mercadona.library.loans.domain.Loan;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepositoryPort {
  Optional<Loan> findById(UUID id);

  Loan save(Loan loan);

  void deleteById(UUID id);
}
