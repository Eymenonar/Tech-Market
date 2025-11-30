package com.onar.eymen.common.core.advice.exception.product;

import com.onar.eymen.common.core.advice.TechMarketException;
import com.onar.eymen.common.core.constant.Codes;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Types;
import org.springframework.http.HttpStatus;

public class InsufficientStockException extends TechMarketException {
  public InsufficientStockException() {
    super(
        Types.Product.INSUFFICIENT_STOCK,
        Codes.Product.INSUFFICIENT_STOCK,
        HttpStatus.BAD_REQUEST,
        Messages.Product.INSUFFICIENT_STOCK);
  }
}
