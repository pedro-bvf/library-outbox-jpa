package com.mercadona.library.books.application.exceptions;

public class BadRequestException extends BusinessException {
  private static final String ERROR_CODE = "C02";

  public BadRequestException(String message) {
    super(message, ERROR_CODE);
  }

  public static BadRequestException of(String message) {
    return new BadRequestException(message);
  }
}
