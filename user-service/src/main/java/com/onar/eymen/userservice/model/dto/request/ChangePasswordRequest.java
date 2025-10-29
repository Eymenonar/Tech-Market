package com.onar.eymen.userservice.model.dto.request;

import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.constant.Regexp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
    @NotBlank(message = "Eski şifre boş bırakılamaz.") String oldPassword,
    @NotBlank(message = "Yeni şifre boş bırakılamaz.")
        @Size(min = 8, max = 50, message = "Yeni parola 8-50 karakter arasında olmalıdır.")
        @Pattern(regexp = Regexp.PASSWORD_PATTERN, message = Messages.User.PASSWORD_REQUIREMENTS)
        String newPassword) {}
