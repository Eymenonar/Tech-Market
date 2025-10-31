package com.onar.eymen.common.core.advice.exception.categories;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class CategoryAlreadyExistsException extends TechMarketException {
  public CategoryAlreadyExistsException() {
    super(
        Types.Categories.CATEGORIES_ALREADY_EXIST,
        Codes.Categories.CATEGORIES_ALREADY_EXIST,
        HttpStatus.CONFLICT,
        Messages.Categories.CATEGORIES_ALREADY_EXIST);
  }
}
