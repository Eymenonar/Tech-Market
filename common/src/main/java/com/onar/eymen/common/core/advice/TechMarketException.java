package com.onar.eymen.common.core.advice;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TechMarketException extends RuntimeException {
  private final String code;
  private final String type;
  private final HttpStatus status;

  public TechMarketException(String code, String type, HttpStatus status, String message) {
    super(message);
    this.code = code;
    this.type = type;
    this.status = status;
  }

  public TechMarketException(
      String code, String type, HttpStatus status, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.type = type;
    this.status = status;
  }
}
