package com.onar.eymen.productapp.service.categories;

import static com.onar.eymen.common.core.response.builder.ResponseBuilder.createNotFoundResponse;
import static com.onar.eymen.common.core.response.builder.ResponseBuilder.createSuccessResponse;

import com.onar.eymen.common.core.advice.exception.categories.CategoryNotFoundException;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.commonjpa.audit.AuditorProvider;
import com.onar.eymen.productapp.model.dto.request.categories.CategoriesCreateRequest;
import com.onar.eymen.productapp.model.dto.request.categories.CategoriesUpdateRequest;
import com.onar.eymen.productapp.model.dto.response.categories.CategoriesResponse;
import com.onar.eymen.productapp.model.entity.Categories;
import com.onar.eymen.productapp.repository.CategoriesRepository;
import com.onar.eymen.productapp.service.domain.categories.CategoriesDomainService;
import com.onar.eymen.productapp.service.validator.categories.CategoriesValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoriesService {
  private final CategoriesValidator validator;
  private final CategoriesRepository repository;
  private final CategoriesDomainService domainService;
  private final AuditorProvider auditorProvider;

  public SuccessResponse<List<CategoriesResponse>> getAllCategories() {
    var response = repository.findAll().stream().map(CategoriesResponse::from).toList();
    if (response.isEmpty()) return createNotFoundResponse(Messages.Categories.CATEGORIES_NOT_FOUND);

    return createSuccessResponse(response, Messages.Categories.CATEGORIES_FOUND);
  }

  public SuccessResponse<List<CategoriesResponse>> getAllMainCategories() {
    var response =
        repository.findByParentCategoryIsNull().stream().map(CategoriesResponse::from).toList();
    if (response.isEmpty())
      return createNotFoundResponse(Messages.Categories.MAIN_CATEGORIES_NOT_FOUND);

    return createSuccessResponse(response, Messages.Categories.MAIN_CATEGORIES_FOUND);
  }

  public SuccessResponse<List<CategoriesResponse>> getParentCategories(Long parentCategoryId) {
    validator.validateParentCategoryExists(parentCategoryId);
    var response =
        repository.findByParentCategoryId(parentCategoryId).stream()
            .map(CategoriesResponse::from)
            .toList();
    if (response.isEmpty())
      return createNotFoundResponse(Messages.Categories.PARENT_CATEGORY_NOT_EXIST);

    return createSuccessResponse(response, Messages.Categories.PARENT_CATEGORY_FOUND);
  }

  public SuccessResponse<CategoriesResponse> getCategoryById(Long categoryId) {
    var category = repository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    var response = CategoriesResponse.from(category);

    return createSuccessResponse(response, Messages.Categories.CATEGORY_FOUND);
  }

  @Transactional
  public SuccessResponse<CategoriesResponse> createNewCategory(CategoriesCreateRequest request) {
    validator.validateUniqueName(request.name());
    validator.validateParentCategoryExists(request.parentCategoryId());
    var category = domainService.buildCategory(request);
    var savedCategory = repository.save(category);
    var response = CategoriesResponse.from(savedCategory);

    return createSuccessResponse(response, Messages.Categories.CATEGORY_SAVED);
  }

  @Transactional
  public SuccessResponse<CategoriesResponse> updateCategory(
      Long id, CategoriesUpdateRequest request) {
    var category = findById(id);
    validator.validateUniqueNameForUpdate(id, request.name());
    validator.validateParentCategoryExists(request.parentCategoryId());
    validator.validateNoCircularReference(id, request.parentCategoryId());
    category.setName(request.name());
    category.setDescription(request.description());
    if (request.parentCategoryId() != null) {
      var parent = findById(request.parentCategoryId());
      category.setParentCategory(parent);
    } else {
      category.setParentCategory(null);
    }
    var updatedCategory = repository.save(category);
    var response = CategoriesResponse.from(updatedCategory);

    return createSuccessResponse(response, Messages.Categories.CATEGORY_UPDATED);
  }

  @Transactional
  public void softDeleteCategory(Long id) {
    var category = findById(id);
    validator.validateNoSubCategories(category.getId());
    repository.softDeleteById(auditorProvider.getCurrentAuditor(), category.getId());
  }

  private Categories findById(Long id) {
    return repository.findById(id).orElseThrow(CategoryNotFoundException::new);
  }
}
