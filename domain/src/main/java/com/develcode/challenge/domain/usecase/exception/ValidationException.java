package com.develcode.challenge.domain.usecase.exception;

public class ValidationException extends RuntimeException {

  public ValidationException(final String message) {
    super(message);
  }

}
