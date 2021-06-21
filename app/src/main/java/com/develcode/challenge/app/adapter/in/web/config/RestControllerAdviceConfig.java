package com.develcode.challenge.app.adapter.in.web.config;

import com.develcode.challenge.domain.usecase.exception.BusinessException;
import com.develcode.challenge.domain.usecase.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestControllerAdviceConfig {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<StandardError> handleBusinessException(final BusinessException ex, HttpServletRequest request) {
    return ResponseEntity.badRequest().body(
        StandardError.builder()
            .timestamp(System.currentTimeMillis())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Business Exception")
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .build()
    );
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<StandardError> handleValidationException(final ValidationException ex, HttpServletRequest request) {
    return ResponseEntity.badRequest().body(
        StandardError.builder()
            .timestamp(System.currentTimeMillis())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Validation Exception")
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .build()
    );
  }


  @Data
  @AllArgsConstructor
  @Builder
  private static class StandardError {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
  }
}
