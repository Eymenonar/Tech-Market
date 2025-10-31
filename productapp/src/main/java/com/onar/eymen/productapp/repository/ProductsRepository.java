package com.onar.eymen.productapp.repository;

import com.onar.eymen.commonjpa.repository.BaseRepository;
import com.onar.eymen.productapp.model.entity.Products;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends BaseRepository<Products, Long> {
  boolean existsBySku(String sku);
}
