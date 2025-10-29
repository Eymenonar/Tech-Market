package com.onar.eymen.common.core.advice.exception.user;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class BreachedPasswordException extends TechMarketException {
  public BreachedPasswordException() {
    super(
        Types.User.BREACHED_PASSWORD,
        Codes.User.BREACHED_PASSWORD,
        HttpStatus.BAD_REQUEST,
        Messages.User.BREACHED_PASSWORD);
  }
}
