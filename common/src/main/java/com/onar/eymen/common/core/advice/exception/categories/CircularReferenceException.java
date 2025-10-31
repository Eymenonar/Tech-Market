package com.onar.eymen.common.core.advice.exception.categories;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class CircularReferenceException extends TechMarketException {
  public CircularReferenceException() {
    super(
        Types.Categories.CIRCULAR_REFERENCE,
        Codes.Categories.CIRCULAR_REFERENCE,
        HttpStatus.CONFLICT,
        Messages.Categories.CIRCULAR_REFERENCE);
  }
}
