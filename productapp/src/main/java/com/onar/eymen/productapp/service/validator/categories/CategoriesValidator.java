package com.onar.eymen.productapp.service.validator.categories;

import com.onar.eymen.common.core.advice.exception.categories.CategoryAlreadyExistsException;
import com.onar.eymen.common.core.advice.exception.categories.CategoryHasSubCategoriesException;
import com.onar.eymen.common.core.advice.exception.categories.CircularReferenceException;
import com.onar.eymen.common.core.advice.exception.categories.ParentCategoryNotFoundException;
import com.onar.eymen.productapp.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriesValidator {
  private final CategoriesRepository repository;

  public void validateUniqueName(String name) {
    if (repository.existsByName(name)) throw new CategoryAlreadyExistsException();
  }

  public void validateUniqueNameForUpdate(Long id, String name) {
    repository
        .findByName(name)
        .ifPresent(
            existing -> {
              if (!existing.getId().equals(id)) {
                throw new CategoryAlreadyExistsException();
              }
            });
  }

  public void validateNoSubCategories(Long categoryId) {
    if (repository.existsByParentCategoryId(categoryId)) {
      throw new CategoryHasSubCategoriesException();
    }
  }

  public void validateNoCircularReference(Long categoryId, Long newParentId) {
    if (newParentId == null) return;

    if (categoryId.equals(newParentId)) {
      throw new CircularReferenceException();
    }
    var currentParent = repository.findById(newParentId);
    while (currentParent.isPresent() && currentParent.get().getParentCategory() != null) {
      if (currentParent.get().getParentCategory().getId().equals(categoryId)) {
        throw new CircularReferenceException();
      }
      currentParent = repository.findById(currentParent.get().getParentCategory().getId());
    }
  }

  public void validateParentCategoryExists(Long parentCategoryId) {
    if (parentCategoryId != null && !repository.existsById(parentCategoryId)) {
      throw new ParentCategoryNotFoundException();
    }
  }
}
