package com.onar.eymen.productapp.controller.categories;

import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.productapp.model.dto.request.categories.CategoriesCreateRequest;
import com.onar.eymen.productapp.model.dto.request.categories.CategoriesUpdateRequest;
import com.onar.eymen.productapp.model.dto.response.categories.CategoriesResponse;
import com.onar.eymen.productapp.service.categories.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "Kategori Yönetimi")
public class CategoriesController {
  private final CategoriesService service;

  @GetMapping("/findAll")
  @ResponseStatus(HttpStatus.FOUND)
  @Operation(summary = "Tüm kategorileri getirir.")
  public SuccessResponse<List<CategoriesResponse>> getAllCategories() {
    return service.getAllCategories();
  }

  @GetMapping("/findAllMain")
  @ResponseStatus(HttpStatus.FOUND)
  @Operation(summary = "Tüm ana kategorileri getirir.")
  public SuccessResponse<List<CategoriesResponse>> getAllMainCategories() {
    return service.getAllMainCategories();
  }

  @GetMapping("/findParent/{id}")
  @ResponseStatus(HttpStatus.FOUND)
  @Operation(summary = "Belirli bir kategorinin alt kategorilerini listeler.")
  public SuccessResponse<List<CategoriesResponse>> getParentCategories(@PathVariable Long id) {
    return service.getParentCategories(id);
  }

  @GetMapping("/findById/{id}")
  @ResponseStatus(HttpStatus.FOUND)
  @Operation(summary = "ID'ye göre kategori getirir.")
  public SuccessResponse<CategoriesResponse> getCategoryById(@PathVariable Long id) {
    return service.getCategoryById(id);
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Yeni kategori oluşturur.")
  public SuccessResponse<CategoriesResponse> createCategory(
      @RequestBody @Valid CategoriesCreateRequest request) {
    return service.createNewCategory(request);
  }

  @PutMapping("/update/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Kategori günceller.")
  public SuccessResponse<CategoriesResponse> updateCategory(
      @PathVariable Long id, @Valid CategoriesUpdateRequest request) {
    return service.updateCategory(id, request);
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Kategori siler (soft-delete).")
  public void softDeleteCategory(@PathVariable Long id) {
    service.softDeleteCategory(id);
  }
}
