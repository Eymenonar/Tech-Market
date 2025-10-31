package com.onar.eymen.productapp.service.validator.products;

import com.onar.eymen.common.core.advice.exception.product.ProductAlreadyExist;
import com.onar.eymen.productapp.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductValidator {
  private final ProductsRepository repository;

  public void validateUniqueSku(String sku) {
    if (repository.existsBySku(sku)) {
      throw new ProductAlreadyExist();
    }
  }
}
