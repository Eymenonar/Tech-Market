package com.onar.eymen.common.core.response.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetail {
  private String className;
  private String methodName;
  private Integer lineNumber;
  private String debugMessage;
  private String exceptionType;

  private static final int NO_LINE_NUMBER = 0;
  private static final String UNKNOWN = "Bilinmiyor";
  private static final String NO_MESSAGE = "Mesaj yok";

  public ErrorDetail(Throwable throwable) {
    boolean hasStackTrace = throwable != null && throwable.getStackTrace().length > 0;
    if (hasStackTrace) fillFromCause(throwable);
    else fillWithDefaults();
  }

  @Override
  public String toString() {
    return String.format(
        "[&s] %s sınıfındaki %s methodunda (satır %d): %s",
        exceptionType != null ? exceptionType : UNKNOWN,
        className != null ? className : UNKNOWN,
        lineNumber != null ? lineNumber : NO_LINE_NUMBER,
        debugMessage != null ? debugMessage : NO_MESSAGE,
        exceptionType != null ? exceptionType : UNKNOWN);
  }

  private void fillFromCause(Throwable throwable) {
    var element = throwable.getStackTrace()[0];
    this.className = element.getClassName();
    this.methodName = element.getMethodName();
    this.lineNumber = element.getLineNumber();
    this.debugMessage = throwable.getMessage();
    this.exceptionType = throwable.getClass().getSimpleName();
  }

  private void fillWithDefaults() {
    this.className = UNKNOWN;
    this.methodName = UNKNOWN;
    this.lineNumber = NO_LINE_NUMBER;
    this.debugMessage = NO_MESSAGE;
    this.exceptionType = UNKNOWN;
  }
}
