package com.mercadona.library.loans.driving.controllers.error;

import com.mercadona.library.api.web_loans.model.ErrorResource;
import com.mercadona.library.api.web_loans.model.ErrorResourceResponse;
import com.mercadona.library.loans.application.exceptions.BusinessException;
import com.mercadona.library.loans.application.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class LoanAPIControllerAdvice {

  @ExceptionHandler({EntityNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResourceResponse handleNotFoundException(BusinessException exception) {
    return getErrorResourceResponse(exception);
  }

  private ErrorResourceResponse getErrorResourceResponse(BusinessException exception) {
    ErrorResource errorResource =
        new ErrorResource()
            .code(exception.getErrorCode())
            .description(exception.getMessage())
            .details(Collections.singletonList(exception.getMessage()));

    return new ErrorResourceResponse().error(errorResource);
  }
}
