package com.onar.eymen.common.core.advice.exception.role;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends TechMarketException {
  public RoleNotFoundException() {
    super(
        Types.Role.NOT_FOUND,
        Codes.Role.NOT_FOUND,
        HttpStatus.BAD_REQUEST,
        Messages.Role.NOT_FOUND);
  }
}
