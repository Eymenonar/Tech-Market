package com.onar.eymen.userservice.model.dto.request;

import com.onar.eymen.common.core.constant.Regexp;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(
    @NotBlank(message = "İsim boş bırakılamaz.")
        @Size(min = 2, max = 100, message = "İsim 2-100 karakter arasında olmalıdır.")
        String newFirstName,
    @NotBlank(message = "Soyisim boş bırakılamaz.")
        @Size(min = 2, max = 100, message = "Soyisim 2-100 karakter arasında olmalıdır.")
        String newLastName,
    @NotBlank(message = "Email boş bırakılamaz")
        @Email(message = "Geçerli bir email adresi giriniz.", regexp = Regexp.EMAIL_PATTERN)
        String newEmail) {}
