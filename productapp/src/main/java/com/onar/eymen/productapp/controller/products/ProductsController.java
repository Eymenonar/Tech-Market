package com.onar.eymen.productapp.controller.products;

import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.productapp.model.dto.request.products.ProductCreateRequest;
import com.onar.eymen.productapp.model.dto.request.products.ProductUpdateRequest;
import com.onar.eymen.productapp.model.dto.response.products.ProductResponse;
import com.onar.eymen.productapp.service.products.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
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

    @GetMapping("/find/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Belirtilen ürünü getirir.")
    public SuccessResponse<ProductResponse> getProductById(@PathVariable Long id) {
    return service.findProduct(id);
  }

  @GetMapping("/findAll")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Tüm ürünleri getirir.")
  public SuccessResponse<List<ProductResponse>> getAllProducts() {
    return service.getAllProducts();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Ürünü sil")
  public void softDeleteProduct(@PathVariable Long id) {
    service.softDeleteProduct(id);
  }

  @PostMapping("/update/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Ürünü günceller.")
  public SuccessResponse<ProductResponse> updateProduct(
      @PathVariable Long id, @Valid ProductUpdateRequest request) {
    return service.updateProduct(id, request);
  }
}
