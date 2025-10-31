package com.onar.eymen.common.core.advice.exception.categories;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class ParentCategoryNotFoundException extends TechMarketException {
  public ParentCategoryNotFoundException() {
    super(
        Types.Categories.PARENT_CATEGORY_NOT_EXIST,
        Codes.Categories.PARENT_CATEGORY_NOT_EXIST,
        HttpStatus.NOT_FOUND,
        Messages.Categories.PARENT_CATEGORY_NOT_EXIST);
  }
}
