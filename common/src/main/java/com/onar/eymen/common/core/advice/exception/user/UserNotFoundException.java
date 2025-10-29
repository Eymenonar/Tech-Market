package com.onar.eymen.common.core.advice.exception.user;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends TechMarketException {
  public UserNotFoundException() {
    super(
        Types.User.NOT_FOUND, Codes.User.NOT_FOUND, HttpStatus.NOT_FOUND, Messages.User.NOT_FOUND);
  }
}
