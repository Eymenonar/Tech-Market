package com.onar.eymen.userservice.security.model.dto.request;

import com.onar.eymen.common.core.constant.Regexp;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Email boş bırakılamaz.")
        @Email(message = "Geçerli bir email adresi giriniz", regexp = Regexp.EMAIL_PATTERN)
        String email,
    @NotBlank(message = "Şifre boş bırakılamaz.") String password) {}
