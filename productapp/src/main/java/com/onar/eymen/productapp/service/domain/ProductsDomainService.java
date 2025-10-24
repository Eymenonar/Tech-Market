package com.onar.eymen.productapp.service.domain;

import com.onar.eymen.productapp.model.dto.request.ProductCreateRequest;
import com.onar.eymen.productapp.model.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsDomainService {
  public Products buildProducts(ProductCreateRequest request) {
    return Products.builder()
        .name(request.name())
        .description(request.description())
        .sku(request.sku())
        .price(request.price())
        .stockQty(request.stockQty())
        .build();
  }
}
