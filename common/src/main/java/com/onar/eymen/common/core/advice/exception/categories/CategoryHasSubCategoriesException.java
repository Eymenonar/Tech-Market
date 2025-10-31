package com.onar.eymen.common.core.advice.exception.categories;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class CategoryHasSubCategoriesException extends TechMarketException {
  public CategoryHasSubCategoriesException() {
    super(
        Types.Categories.CATEGORY_HAS_SUB_CATEGORIES,
        Codes.Categories.CATEGORY_HAS_SUB_CATEGORIES,
        HttpStatus.CONFLICT,
        Messages.Categories.CATEGORY_HAS_SUB_CATEGORIES);
  }
}
