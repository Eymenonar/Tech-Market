package com.onar.eymen.userservice.model.dto.request;

import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Regexp;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "İsim boş bırakılamaz.") String firstName,
    @NotBlank(message = "Soyisim boş bırakılamaz.") String lastName,
    @NotBlank(message = "Email boş bırakılamaz.")
        @Email(message = "Geçerli bir email adresi giriniz.", regexp = Regexp.EMAIL_PATTERN)
        String email,
    @NotBlank(message = "Şifre boş bırakılamaz.")
        @Size(min = 8, max = 50, message = "Şifre 8-50 karakter arasında olmalıdır.")
        @Pattern(regexp = Regexp.PASSWORD_PATTERN, message = Messages.User.PASSWORD_REQUIREMENTS)
        String password) {}
