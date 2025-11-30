package com.onar.eymen.productapp.controller.products;

import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.productapp.model.dto.request.products.ProductCreateRequest;
import com.onar.eymen.productapp.model.dto.request.products.ProductUpdateRequest;
import com.onar.eymen.productapp.model.dto.request.products.StockDecreaseRequest;
import com.onar.eymen.productapp.model.dto.response.products.ProductResponse;
import com.onar.eymen.productapp.service.products.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Ürün Yönetimi", description = "Ürün ekleme, silme, filtreleme")
public class ProductsController {
  private final ProductsService service;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Yeni bir ürün kaydı oluşturur.")
  public SuccessResponse<ProductResponse> create(@RequestBody @Valid ProductCreateRequest request) {
    return service.createProduct(request);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Belirtilen ürünü getirir.")
  public SuccessResponse<ProductResponse> getProductById(@PathVariable Long id) {
    return service.findProduct(id);
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Ürünleri sayfalı getirir")
  public SuccessResponse<Page<ProductResponse>> getAllProducts(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sortBy,
      @RequestParam(defaultValue = "ASC") String direction) {
    List<String> allowedSortFields = List.of("id", "name", "price", "stockQty", "createdAt");
    if (!allowedSortFields.contains(sortBy)) {
      sortBy = "id";
    }
    return service.getAllProductsPaged(page, size, sortBy, direction);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Ürünü sil")
  public void softDeleteProduct(@PathVariable Long id) {
    service.softDeleteProduct(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Ürünü günceller.")
  public SuccessResponse<ProductResponse> updateProduct(
      @PathVariable Long id, @Valid ProductUpdateRequest request) {
    return service.updateProduct(id, request);
  }

  @PostMapping("/{id}/stock/decrease")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Ürün stoğunu düşürür. (order-service için)")
  public void decreaseStock(
      @PathVariable Long id, @RequestBody @Valid StockDecreaseRequest request) {
    service.decreaseStock(id, request.quantity());
  }

  @PostMapping("/{id}/stock/increase")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Ürün stoğunu arttırır. (order-service için, sipariş iptal olma durumunda)")
  public void increaseStock(
      @PathVariable Long id, @RequestBody @Valid StockDecreaseRequest request) {
    service.increaseStock(id, request.quantity());
  }

  @GetMapping("/{id}/stock/check")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Stok kontrolü yapar.")
  public boolean checkStock(
      @PathVariable Long id, @Valid @ModelAttribute StockDecreaseRequest request) {
    return service.isStockAvailable(id, request.quantity());
  }
}
