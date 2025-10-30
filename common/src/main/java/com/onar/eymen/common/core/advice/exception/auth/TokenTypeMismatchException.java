package com.onar.eymen.common.core.advice.exception.auth;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class TokenTypeMismatchException extends TechMarketException {
  public TokenTypeMismatchException() {
    super(
        Types.Auth.TOKEN_TYPE_MISMATCH,
        Codes.Auth.TOKEN_TYPE_MISMATCH,
        HttpStatus.BAD_REQUEST,
        Messages.Auth.TOKEN_TYPE_MISMATCH);
  }

  public TokenTypeMismatchException(String tokenType) {
    super(
        Types.Auth.TOKEN_TYPE_MISMATCH,
        Codes.Auth.TOKEN_TYPE_MISMATCH,
        HttpStatus.BAD_REQUEST,
        Messages.Auth.TOKEN_TYPE_MISMATCH + ": " + tokenType);
  }
}
