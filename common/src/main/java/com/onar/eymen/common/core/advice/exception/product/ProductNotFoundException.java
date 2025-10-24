package com.onar.eymen.common.core.advice.exception.product;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends TechMarketException {
  public ProductNotFoundException() {
    super(
        Types.Product.PRODUCT_NOT_EXIST,
        Codes.Product.PRODUCT_NOT_EXIST,
        HttpStatus.NOT_FOUND,
        Messages.Product.PRODUCT_NOT_FOUND);
  }
}
