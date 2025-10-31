package com.onar.eymen.common.core.advice.exception.categories;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends TechMarketException {
  public CategoryNotFoundException() {
    super(
        Types.Categories.CATEGORY_NOT_FOUND,
        Codes.Categories.CATEGORY_NOT_FOUND,
        HttpStatus.NOT_FOUND,
        Messages.Categories.CATEGORY_NOT_FOUND);
  }
}
