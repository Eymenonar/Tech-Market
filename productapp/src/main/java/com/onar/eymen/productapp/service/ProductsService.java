package com.onar.eymen.productapp.service;

import static com.onar.eymen.common.core.response.builder.ResponseBuilder.createSuccessResponse;

import com.onar.eymen.common.core.advice.exception.product.ProductAlreadyExist;
import com.onar.eymen.common.core.advice.exception.product.ProductNotFoundException;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.commonjpa.audit.AuditorProvider;
import com.onar.eymen.productapp.model.dto.request.ProductCreateRequest;
import com.onar.eymen.productapp.model.dto.request.ProductUpdateRequest;
import com.onar.eymen.productapp.model.dto.response.ProductResponse;
import com.onar.eymen.productapp.model.entity.Products;
import com.onar.eymen.productapp.repository.ProductsRepository;
import com.onar.eymen.productapp.service.domain.ProductsDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductsService {
  private final ProductsRepository repository;
  private final AuditorProvider auditorProvider;
  private final ProductsDomainService domainService;

  @Transactional
  public SuccessResponse<ProductResponse> createProduct(ProductCreateRequest request) {
    if (request.sku() != null && repository.existsBySku(request.sku()))
      throw new ProductAlreadyExist();

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

  public Products findById(Long id) {
    return repository.findById(id).orElseThrow(ProductNotFoundException::new);
  }

  public SuccessResponse<ProductResponse> findProduct(Long id) {
    var product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
    var response = ProductResponse.from(product);

    return createSuccessResponse(response, Messages.Product.PRODUCT_FOUND);
  }
}
