package com.onar.eymen.common.core.advice.exception.user;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends TechMarketException {
  public EmailAlreadyExistsException() {
    super(
        Types.User.EMAIL_ALREADY_EXISTS,
        Codes.User.EMAIL_ALREADY_EXISTS,
        HttpStatus.BAD_REQUEST,
        Messages.User.EMAIL_ALREADY_EXISTS);
  }
}
