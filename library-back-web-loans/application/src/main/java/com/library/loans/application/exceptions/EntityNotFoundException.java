package com.library.loans.application.exceptions;

public class EntityNotFoundException extends BusinessException {
  private static final String ERROR_CODE = "L01";

  public EntityNotFoundException(String message) {
    super(message, ERROR_CODE);
  }

  public static EntityNotFoundException of(String text, Object id) {
    return new EntityNotFoundException(String.format(text, id));
  }
}
