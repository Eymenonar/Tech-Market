package com.onar.eymen.productapp.model.dto.response.products;

import com.onar.eymen.productapp.model.entity.Products;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
    Long id,
    String name,
    String description,
    String sku,
    BigDecimal price,
    Integer stockQty,
    boolean isActive,
    LocalDateTime createdAt) {
  public static ProductResponse from(Products product) {
    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getSku(),
        product.getPrice(),
        product.getStockQty(),
        product.getAudit().isActive(),
        product.getAudit().getCreatedAt());
  }
}
