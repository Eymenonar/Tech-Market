package com.onar.eymen.productapp.model.dto.request.products;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductCreateRequest(
    @NotBlank @Size(max = 200) String name,
    @Size(max = 500) String description,
    @Size(max = 80) String sku,
    @NotBlank @DecimalMin(value = "0.00") BigDecimal price,
    @PositiveOrZero Integer stockQty) {}
