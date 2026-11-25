package com.mercadona.library.books.application.exceptions;

public class NotFoundException extends BusinessException {
  private static final String ERROR_CODE = "C01";

	public NotFoundException(String message) {
		super(message, ERROR_CODE);
	}

	public static NotFoundException of(String text, Object id) {
		return new NotFoundException(String.format(text, id));
	}

}
