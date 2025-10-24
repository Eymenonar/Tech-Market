package com.onar.eymen.productapp.controller;

import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.productapp.model.dto.request.ProductCreateRequest;
import com.onar.eymen.productapp.model.dto.response.ProductResponse;
import com.onar.eymen.productapp.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Ürün Yönetimi", description = "Ürün ekleme, silme, filtreleme")
public class ProductsController {
  private final ProductsService service;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Yeni bir ürün kaydı oluşturur.")
  public SuccessResponse<ProductResponse> create(@RequestBody @Valid ProductCreateRequest request) {
    return service.createProduct(request);
  }

  @GetMapping("/find")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Belirtilen ürünü getirir.")
  public SuccessResponse<ProductResponse> getProductById(Long id) {
    return service.findById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Ürünü sil")
  public void softDeleteProduct(@PathVariable Long id) {
    service.softDeleteProduct(id);
  }
}
