package com.onar.eymen.productapp.model.dto.response.categories;

import com.onar.eymen.productapp.model.entity.Categories;
import java.time.LocalDateTime;

public record CategoriesResponse(
    Long id,
    String name,
    String description,
    Long parentCategoryId,
    String parentCategoryName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {

  public static CategoriesResponse from(Categories category) {
    return new CategoriesResponse(
        category.getId(),
        category.getName(),
        category.getDescription(),
        category.getParentCategory() != null ? category.getParentCategory().getId() : null,
        category.getParentCategory() != null ? category.getParentCategory().getName() : null,
        category.getAudit().getCreatedAt(),
        category.getAudit().getUpdatedAt());
  }
}
