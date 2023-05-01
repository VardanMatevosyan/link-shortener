package com.example.link_shortener.exception;

public class NotFoundUrlException extends RuntimeException {

  public NotFoundUrlException(String message) {
    super(message);
  }

  public NotFoundUrlException(String message, Throwable cause) {
    super(message, cause);
  }
}
