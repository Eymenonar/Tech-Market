package com.onar.eymen.common.core.advice.exception.user;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class OldPasswordMismatchException extends TechMarketException {
  public OldPasswordMismatchException() {
    super(
        Types.User.OLD_PASSWORD_MISMATCH,
        Codes.User.OLD_PASSWORD_MISMATCH,
        HttpStatus.BAD_REQUEST,
        Messages.User.OLD_PASSWORD_MISMATCH);
  }
}
