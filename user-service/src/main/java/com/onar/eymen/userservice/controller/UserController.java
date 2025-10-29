package com.onar.eymen.userservice.controller;

import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.userservice.model.dto.request.RegisterRequest;
import com.onar.eymen.userservice.model.dto.request.UpdateProfileRequest;
import com.onar.eymen.userservice.model.dto.response.UserResponse;
import com.onar.eymen.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
@Tag(name = "Kullanıcı işlemleri")
public class UserController {
  private final UserService service;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Yeni bir kullanıcı kaydı oluşturur.")
  public SuccessResponse<UserResponse> registerUser(@RequestBody @Valid RegisterRequest request) {
    return service.registerUser(request);
  }

  @PutMapping("/{id}/update")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Kullanıcı profilini günceller.")
  public SuccessResponse<UserResponse> updateUser(
      @PathVariable Long id, @RequestBody @Valid UpdateProfileRequest request) {
    return service.updateProfile(id, request);
  }
}
