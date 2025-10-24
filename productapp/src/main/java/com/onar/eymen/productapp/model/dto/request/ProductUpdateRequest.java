package com.onar.eymen.productapp.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductUpdateRequest(
    @Size(max = 200) String name,
    @Size(max = 5000) String description,
    @Size(max = 80) String sku,
    @DecimalMin(value = "0.00") BigDecimal price,
    @PositiveOrZero Integer stockQty) {}
