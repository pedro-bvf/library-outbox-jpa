package com.library.loans.application.ports.driving;

import com.library.loans.domain.Loan;
import java.util.UUID;

public interface LoanServicePort {
  Loan getLoanById(UUID id);

  Loan createLoan(Loan loan);

  void cancelLoan(UUID id);

  Loan returnLoan(UUID id);
}
