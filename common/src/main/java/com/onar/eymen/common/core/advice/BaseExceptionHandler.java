package com.onar.eymen.common.core.advice;

import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import com.onar.eymen.common.core.response.error.ErrorResponse;
import java.util.HashMap;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

  @ExceptionHandler({Exception.class, RuntimeException.class})
  public ResponseEntity<ErrorResponse<?>> handleGenericException(Exception exception) {
    return buildResponseEntity(new ErrorResponse<>(exception));
  }

  @ExceptionHandler(TechMarketException.class)
  public ResponseEntity<?> handleTechMarketException(TechMarketException ex) {
    var error =
        new ErrorResponse<>(ex.getType(), ex.getCode(), ex.getMessage(), ex.getStatus(), ex);
    return buildResponseEntity(error);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse<?>> handleIllegalArgumentException(
      IllegalArgumentException exception) {
    var error =
        new ErrorResponse<>(
            Types.Error.ILLEGAL_ARGUMENT,
            Codes.ILLEGAL_ARGUMENT,
            Messages.Error.INVALID_ARGUMENT,
            HttpStatus.BAD_REQUEST,
            exception);

    return buildResponseEntity(error);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorResponse<?>> handleNoSuchElementException(
      NoSuchElementException exception) {
    var error =
        new ErrorResponse<>(
            Types.Error.NO_SUCH_ELEMENT,
            Codes.NO_SUCH_ELEMENT,
            Messages.Error.NO_SUCH_ELEMENT,
            HttpStatus.NOT_FOUND,
            exception);

    return buildResponseEntity(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse<?>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    var validationErrors = extractValidationErrors(exception);
    var error =
        new ErrorResponse<>(
            Types.Error.VALIDATION,
            Codes.METHOD_ARGUMENT_NOT_VALID,
            validationErrors,
            HttpStatus.BAD_REQUEST,
            exception);

    return buildResponseEntity(error);
  }

  public ResponseEntity<ErrorResponse<?>> buildResponseEntity(ErrorResponse<?> errorResponse) {
    return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
  }

  private HashMap<String, String> extractValidationErrors(MethodArgumentNotValidException e) {
    var validationErrors = new HashMap<String, String>();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    return validationErrors;
  }
}
