package com.onar.eymen.productapp.repository;

import com.onar.eymen.commonjpa.repository.BaseRepository;
import com.onar.eymen.productapp.model.entity.Categories;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends BaseRepository<Categories, Long> {
  boolean existsByName(String name);

  Optional<Categories> findByName(String name);

  List<Categories> findByParentCategoryIsNull();

  List<Categories> findByParentCategoryId(Long parentCategoryId);

  boolean existsByParentCategoryId(Long parentCategoryId);
}
