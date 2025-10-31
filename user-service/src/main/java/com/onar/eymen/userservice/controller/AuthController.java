package com.onar.eymen.userservice.controller;

import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.userservice.security.model.dto.request.LoginRequest;
import com.onar.eymen.userservice.security.model.dto.request.RefreshTokenRequest;
import com.onar.eymen.userservice.security.model.dto.response.LoginResponse;
import com.onar.eymen.userservice.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(
    name = "Kimlik Doğrulama & Token Yönetimi",
    description = "Kimlik doğrulama ve token yönetimi işlemleri")
@SecurityRequirements()
public class AuthController {
  private final AuthService service;

  @PostMapping("/login")
  public SuccessResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    return service.login(request.email(), request.password());
  }

  @PostMapping("/refreshToken")
  public SuccessResponse<LoginResponse> getRefreshToken(@RequestBody RefreshTokenRequest request) {
    return service.tokenRefresh(request.refreshToken());
  }
}
