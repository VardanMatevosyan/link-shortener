package com.example.link_shortener.config.exception;

import com.example.link_shortener.exception.NotFoundUrlException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorDetails {

  String status;
  String message;
  String details;

  public static ErrorDetails build(HttpStatus status,
                                    NotFoundUrlException exp,
                                    WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails();
    errorDetails.setStatus(status.getReasonPhrase());
    errorDetails.setMessage(exp.getMessage());
    errorDetails.setDetails(request.getDescription(false));
    return errorDetails;
  }
}
