package com.library.loans.driven.repositories.adapters;

import com.library.loans.application.ports.driven.LoanRepositoryPort;
import com.library.loans.domain.Loan;
import com.library.loans.driven.repositories.LoanMOJpaRepository;
import com.library.loans.driven.repositories.mappers.LoanMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LoanRepositoryAdapter implements LoanRepositoryPort {

  private final LoanMOJpaRepository repository;

  private final LoanMapper mapper;

  @Override
  public Optional<Loan> findById(UUID id) {
    return repository.findById(id).map(mapper::toDomain);
  }

  @Override
  public Loan save(Loan loan) {
    var loanModel = mapper.toModel(loan);
    var saved = repository.save(loanModel);
    saved.setLoanDetails(mapper.mapBooksToLoanDetails(loan.books(), saved));
    return mapper.toDomain(repository.save(saved));
  }

  @Override
  public void deleteById(UUID id) {
    repository.deleteById(id);
  }
}
