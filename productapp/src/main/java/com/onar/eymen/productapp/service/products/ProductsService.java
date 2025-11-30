package com.onar.eymen.productapp.service.products;

import static com.onar.eymen.common.core.response.builder.ResponseBuilder.createSuccessResponse;

import com.onar.eymen.common.core.advice.exception.categories.CategoryNotFoundException;
import com.onar.eymen.common.core.advice.exception.product.InsufficientStockException;
import com.onar.eymen.common.core.advice.exception.product.ProductNotFoundException;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.commonjpa.audit.AuditorProvider;
import com.onar.eymen.productapp.model.dto.request.products.ProductCreateRequest;
import com.onar.eymen.productapp.model.dto.request.products.ProductUpdateRequest;
import com.onar.eymen.productapp.model.dto.response.products.ProductResponse;
import com.onar.eymen.productapp.model.entity.Products;
import com.onar.eymen.productapp.repository.CategoriesRepository;
import com.onar.eymen.productapp.repository.ProductsRepository;
import com.onar.eymen.productapp.service.domain.products.ProductsDomainService;
import com.onar.eymen.productapp.service.validator.products.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
  private final CategoriesRepository categoriesRepository;

  public SuccessResponse<Page<ProductResponse>> getAllProductsPaged(
      int page, int size, String sortBy, String direction) {

    Sort.Direction sortDirection =
        direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

    Page<Products> productPage = repository.findAll(pageable);
    Page<ProductResponse> responsePage = productPage.map(ProductResponse::from);

    return createSuccessResponse(responsePage, Messages.Product.PRODUCT_FOUND);
  }

  @Transactional
  public SuccessResponse<ProductResponse> createProduct(ProductCreateRequest request) {
    validator.validateUniqueSku(request.sku());
    validator.validateCategoryExists(request.categoryId());
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
    validator.validateCategoryExists(request.categoryId());
    product.setName(request.name());
    product.setDescription(request.description());
    product.setPrice(request.price());
    product.setStockQty(request.stockQty());

    if (request.categoryId() != null) {
      var category =
          categoriesRepository
              .findById(request.categoryId())
              .orElseThrow(CategoryNotFoundException::new);
      product.setCategory(category);
    } else {
      product.setCategory(null);
    }

    var updatedProduct = repository.save(product);
    var response = ProductResponse.from(updatedProduct);

    return createSuccessResponse(response, Messages.Product.UPDATED);
  }

  public SuccessResponse<ProductResponse> findProduct(Long id) {
    var product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
    var response = ProductResponse.from(product);

    return createSuccessResponse(response, Messages.Product.PRODUCT_FOUND);
  }

  @Transactional
  public void decreaseStock(Long id, Integer quantity) {
    var product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
    if (product.getStockQty() < quantity) {
      throw new InsufficientStockException();
    }
    product.setStockQty(product.getStockQty() - quantity);
    repository.save(product);
  }

  @Transactional
  public void increaseStock(Long id, Integer quantity) {
    var product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
    product.setStockQty(product.getStockQty() + quantity);
    repository.save(product);
  }

  public boolean isStockAvailable(Long id, Integer quantity) {
    var product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
    return product.getStockQty() >= quantity;
  }

  private Products findById(Long id) {
    return repository.findById(id).orElseThrow(ProductNotFoundException::new);
  }
}
