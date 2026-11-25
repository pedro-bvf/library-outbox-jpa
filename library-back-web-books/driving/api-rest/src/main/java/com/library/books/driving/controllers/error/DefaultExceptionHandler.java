package com.library.books.driving.controllers.error;

import com.library.api.web_books.model.ErrorResource;
import com.library.api.web_books.model.ErrorResourceResponse;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Fallback handling for framework-level exceptions not caught by CustomControllerAdvice
// (bean validation, malformed bodies, wrong content-type/method, uncaught exceptions).
// Native replacement for fwkcna-lib-error-handler's DefaultExceptionHandler, same error codes/statuses.
@Slf4j
@Order
@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    var details =
        exception.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + " " + error.getDefaultMessage())
            .collect(Collectors.toList());

    var error = new ErrorResource().code("SB03").description("Validation error").details(details);
    return new ResponseEntity<>(new ErrorResourceResponse().error(error), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity handleHttpMessageNotReadable(
      HttpMessageNotReadableException exception,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    log.error("Http message not readable exception", exception);
    return build("SB03", exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException exception,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    log.error("Unsupported media type: ", exception);
    return build("G05", exception.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @Override
  public ResponseEntity handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException exception,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    log.error("Unsupported method", exception);
    return build("G06", exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResourceResponse> handleIllegalArgumentException(
      IllegalArgumentException exception) {
    log.error("Illegal argument exception: ", exception);
    return build("SB03", exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResourceResponse> handleException(Exception exception) {
    log.error("Received not properly handled exception:", exception);
    return build(
        "G04",
        exception.getClass().getSimpleName() + ": " + exception.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  static ResponseEntity<ErrorResourceResponse> build(
      String code, String description, HttpStatus status) {
    var error = new ErrorResource().code(code).description(description);
    return new ResponseEntity<>(new ErrorResourceResponse().error(error), status);
  }
}
