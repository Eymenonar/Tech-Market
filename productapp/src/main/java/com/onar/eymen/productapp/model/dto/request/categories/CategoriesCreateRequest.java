package com.onar.eymen.productapp.model.dto.request.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriesCreateRequest(
    @NotBlank(message = "Kategori adı boş olamaz.")
        @Size(min = 2, max = 100, message = "Kategori adı 2-100 karakter arası olmalı")
        String name,
    @Size(max = 500, message = "Açıklama en fazla 500 karakter olabilir.") String description,
    Long parentCategoryId) {}
