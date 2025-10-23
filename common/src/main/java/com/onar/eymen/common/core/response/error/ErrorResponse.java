package com.onar.eymen.common.core.response.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import com.onar.eymen.common.core.response.common.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse<T> extends Response {
  private HttpStatus status;
  @JsonIgnore private ErrorDetail errorDetail;

  public ErrorResponse(
      String type, String code, T message, HttpStatus status, Throwable throwable) {
    super(type, code, message, false);
    this.status = status;
    populateErrorDetails(throwable);
  }

  public ErrorResponse(Throwable throwable) {
    super(Types.Error.DEFAULT, Codes.UNEXPECTED, Messages.Error.UNEXPECTED, false);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    populateErrorDetails(throwable);
  }

  public ErrorResponse(ErrorResponse<?> response) {
    super(response.type, response.code, response.message, response.success, response.traceId);
    this.status = response.status;
    this.errorDetail = response.errorDetail;
  }

  private void populateErrorDetails(Throwable throwable) {
    boolean hasStackTrace = throwable != null && throwable.getStackTrace().length > 0;
    if (hasStackTrace) {
      this.errorDetail = new ErrorDetail(throwable);
    }
  }
}
