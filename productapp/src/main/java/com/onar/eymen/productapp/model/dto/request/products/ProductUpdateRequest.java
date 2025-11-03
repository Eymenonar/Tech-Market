package com.onar.eymen.productapp.model.dto.request.products;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductUpdateRequest(
    @NotBlank(message = "Ürün ismi boş bırakılamaz.") @Size(max = 200) String name,
    @Size(max = 5000) String description,
    @NotNull(message = "Ürün fiyatı boş bırakılamaz.") @DecimalMin(value = "0.00") BigDecimal price,
    @NotNull(message = "Ürün stoğu boş bırakılamaz.") @PositiveOrZero Integer stockQty,
    Long categoryId) {
}
