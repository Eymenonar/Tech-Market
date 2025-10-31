package com.onar.eymen.productapp.service.products;

import static com.onar.eymen.common.core.response.builder.ResponseBuilder.createNotFoundResponse;
import static com.onar.eymen.common.core.response.builder.ResponseBuilder.createSuccessResponse;

import com.onar.eymen.common.core.advice.exception.product.ProductNotFoundException;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.commonjpa.audit.AuditorProvider;
import com.onar.eymen.productapp.model.dto.request.products.ProductCreateRequest;
import com.onar.eymen.productapp.model.dto.request.products.ProductUpdateRequest;
import com.onar.eymen.productapp.model.dto.response.products.ProductResponse;
import com.onar.eymen.productapp.model.entity.Products;
import com.onar.eymen.productapp.repository.ProductsRepository;
import com.onar.eymen.productapp.service.domain.products.ProductsDomainService;
import com.onar.eymen.productapp.service.validator.products.ProductValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductsService {
  private final ProductValidator validator;
  private final ProductsRepository repository;
  private final AuditorProvider auditorProvider;
  private final ProductsDomainService domainService;

  public SuccessResponse<List<ProductResponse>> getAllProducts() {
    var response = repository.findAll().stream().map(ProductResponse::from).toList();
    if (response.isEmpty()) return createNotFoundResponse(Messages.Product.PRODUCT_NOT_FOUND);

    return createSuccessResponse(response, Messages.Product.PRODUCT_FOUND);
  }

  @Transactional
  public SuccessResponse<ProductResponse> createProduct(ProductCreateRequest request) {
    validator.validateUniqueSku(request.sku());
    var product = domainService.buildProducts(request);
    var newProduct = repository.save(product);
    var response = ProductResponse.from(newProduct);

    return createSuccessResponse(response, Messages.Product.SAVED);
  }

  @Transactional
  public void softDeleteProduct(Long id) {
    var product = repository.findById(id);
    if (product.isEmpty()) throw new ProductNotFoundException();
    repository.softDeleteById(auditorProvider.getCurrentAuditor(), id);
  }

  @Transactional
  public SuccessResponse<ProductResponse> updateProduct(Long id, ProductUpdateRequest request) {
    var product = findById(id);
    product.setName(request.name());
    product.setDescription(request.description());
    product.setPrice(request.price());
    product.setStockQty(request.stockQty());
    var updatedProduct = repository.save(product);
    var response = ProductResponse.from(updatedProduct);

    return createSuccessResponse(response, Messages.Product.UPDATED);
  }

  private Products findById(Long id) {
    return repository.findById(id).orElseThrow(ProductNotFoundException::new);
  }

  public SuccessResponse<ProductResponse> findProduct(Long id) {
    var product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
    var response = ProductResponse.from(product);

    return createSuccessResponse(response, Messages.Product.PRODUCT_FOUND);
  }
}
