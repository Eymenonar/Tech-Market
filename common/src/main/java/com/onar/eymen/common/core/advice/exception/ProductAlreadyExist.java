package com.onar.eymen.common.core.advice.exception;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class ProductAlreadyExist extends TechMarketException {
  public ProductAlreadyExist() {
    super(
        Types.Product.PRODUCT_ALREADY_EXIST,
        Codes.Product.PRODUCT_ALREADY_EXIST,
        HttpStatus.CONFLICT,
        Messages.Product.PRODUCT_ALREADY_EXIST);
  }
}
