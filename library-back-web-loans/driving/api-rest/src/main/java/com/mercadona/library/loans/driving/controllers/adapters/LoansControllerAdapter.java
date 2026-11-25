package com.mercadona.library.loans.driving.controllers.adapters;

import com.mercadona.library.api.web_loans.definition.server.LoansApi;
import com.mercadona.library.api.web_loans.model.LoanResourceRequest;
import com.mercadona.library.api.web_loans.model.LoanResourceResponse;
import com.mercadona.library.loans.application.ports.driving.LoanServicePort;
import com.mercadona.library.loans.driving.controllers.mappers.LoansDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class LoansControllerAdapter implements LoansApi {
  private final LoanServicePort loansService;
  private final LoansDTOMapper loansDTOMapper;

  @Override
  public ResponseEntity<LoanResourceResponse> createLoan(LoanResourceRequest request) {
    var loanDomain = loansDTOMapper.fromLoanResourceRequest(request);
    var createdLoan = loansService.createLoan(loanDomain);
    var loanDto = loansDTOMapper.toLoanResourceResponse(createdLoan);
    return ResponseEntity.status(HttpStatus.CREATED).body(loanDto);
  }

  @Override
  public ResponseEntity<LoanResourceResponse> getLoanById(UUID loanId) {
    var loanDomain = loansService.getLoanById(loanId);
    var loanDto = loansDTOMapper.toLoanResourceResponse(loanDomain);
    return ResponseEntity.ok(loanDto);
  }

  @Override
  public ResponseEntity<LoanResourceResponse> cancelLoanById(UUID loanId) {
    loansService.cancelLoan(loanId);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<LoanResourceResponse> returnLoanById(UUID loanId) {
    var returnedLoan = loansService.returnLoan(loanId);
    var loanDto = loansDTOMapper.toLoanResourceResponse(returnedLoan);
    return ResponseEntity.ok(loanDto);
  }
}
