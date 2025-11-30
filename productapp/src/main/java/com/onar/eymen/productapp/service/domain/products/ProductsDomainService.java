package com.onar.eymen.productapp.service.domain.products;

import com.onar.eymen.common.core.advice.exception.categories.CategoryNotFoundException;
import com.onar.eymen.productapp.model.dto.request.products.ProductCreateRequest;
import com.onar.eymen.productapp.model.entity.Products;
import com.onar.eymen.productapp.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsDomainService {
  private final CategoriesRepository categoriesRepository;

  public Products buildProducts(ProductCreateRequest request) {
    var product =
        Products.builder()
            .name(request.name())
            .description(request.description())
            .sku(request.sku())
            .price(request.price())
            .stockQty(request.stockQty())
            .build();

    if (request.categoryId() != null) {
      var category =
          categoriesRepository
              .findById(request.categoryId())
              .orElseThrow(CategoryNotFoundException::new);
      product.setCategory(category);
    }
    return product;
  }
}
