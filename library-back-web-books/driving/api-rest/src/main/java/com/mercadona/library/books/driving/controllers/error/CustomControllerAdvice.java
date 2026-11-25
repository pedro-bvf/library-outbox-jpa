package com.mercadona.library.books.driving.controllers.error;

import com.mercadona.library.api.web_books.model.ErrorResource;
import com.mercadona.library.api.web_books.model.ErrorResourceResponse;
import com.mercadona.library.books.application.exceptions.BusinessException;
import com.mercadona.library.books.application.exceptions.BadRequestException;
import com.mercadona.library.books.application.exceptions.ConflictException;
import com.mercadona.library.books.application.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RequiredArgsConstructor
@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResourceResponse handleExampleNotFoundException(BusinessException exception) {
      return getErrorResourceResponse(exception);
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResourceResponse handleBadRequestException(BusinessException exception) {
      return getErrorResourceResponse(exception);
    }

    @ExceptionHandler({ConflictException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResourceResponse handleConflictException(BusinessException exception) {
      return getErrorResourceResponse(exception);
    }

  private ErrorResourceResponse getErrorResourceResponse(BusinessException exception) {
    ErrorResource errorResource = new ErrorResource()
          .code(exception.getErrorCode())
          .description(exception.getMessage())
          .details(Collections.singletonList(exception.getMessage()));

    return new ErrorResourceResponse().error(errorResource);
  }
}
