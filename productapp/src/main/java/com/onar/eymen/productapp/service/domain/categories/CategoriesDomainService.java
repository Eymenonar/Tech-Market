package com.onar.eymen.productapp.service.domain.categories;

import com.onar.eymen.common.core.advice.exception.categories.ParentCategoryNotFoundException;
import com.onar.eymen.productapp.model.dto.request.categories.CategoriesCreateRequest;
import com.onar.eymen.productapp.model.entity.Categories;
import com.onar.eymen.productapp.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriesDomainService {
  private final CategoriesRepository repository;

  public Categories buildCategory(CategoriesCreateRequest request) {
    var category = new Categories();
    category.setName(request.name());
    category.setDescription(request.description());
    if (request.parentCategoryId() != null) {
      var parent =
          repository
              .findById(request.parentCategoryId())
              .orElseThrow(ParentCategoryNotFoundException::new);
      category.setParentCategory(parent);
    }

    return category;
  }
}
