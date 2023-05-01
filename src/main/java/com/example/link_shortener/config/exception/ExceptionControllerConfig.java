package com.example.link_shortener.config.exception;


import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.link_shortener.exception.NotFoundUrlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class ExceptionControllerConfig {

  @ExceptionHandler(value = NotFoundUrlException.class)
  public ResponseEntity<ErrorDetails> handleNotFoundUrlException(NotFoundUrlException exp,
                                                                WebRequest request) {
    log.error(exp.getMessage() + " from " + request.getDescription(false));
    ErrorDetails errorDetails = ErrorDetails.build(NOT_FOUND, exp, request);
    return ResponseEntity.status(NOT_FOUND).body(errorDetails);
  }

}
