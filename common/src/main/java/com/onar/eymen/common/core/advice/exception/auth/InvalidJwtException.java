package com.onar.eymen.common.core.advice.exception.auth;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class InvalidJwtException extends TechMarketException {
  public InvalidJwtException() {
    super(
        Types.Auth.INVALID_JWT,
        Codes.Auth.INVALID_JWT,
        HttpStatus.UNAUTHORIZED,
        Messages.Auth.INVALID_JWT);
  }
}
