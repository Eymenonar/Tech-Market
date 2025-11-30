package com.onar.eymen.productapp.model.dto.request.products;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockDecreaseRequest(
    @NotNull(message = "Miktar boş olamaz.") @Positive(message = "Miktar sıfırdan büyük olmalı.")
        Integer quantity) {}
