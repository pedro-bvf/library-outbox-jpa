package com.library.books.application.exceptions;

public class ConflictException extends BusinessException {
  private static final String ERROR_CODE = "C03";

  public ConflictException(String message) {
    super(message, ERROR_CODE);
  }

  public static ConflictException of(String text, Object id) {
    return new ConflictException(String.format(text, id));
  }
}
