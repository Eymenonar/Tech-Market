package com.onar.eymen.productapp.service.validator.products;

import com.onar.eymen.common.core.advice.exception.categories.CategoryNotFoundException;
import com.onar.eymen.common.core.advice.exception.product.ProductAlreadyExist;
import com.onar.eymen.productapp.repository.CategoriesRepository;
import com.onar.eymen.productapp.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductValidator {
  private final ProductsRepository repository;
  private final CategoriesRepository categoriesRepository;

  public void validateUniqueSku(String sku) {
    if (repository.existsBySku(sku)) {
      throw new ProductAlreadyExist();
    }
  }

  public void validateCategoryExists(Long categoryId) {
    if (categoryId != null && !categoriesRepository.existsById(categoryId)) {
      throw new CategoryNotFoundException();
    }
  }
}
