package com.onar.eymen.userservice.controller;

import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.userservice.model.dto.request.ChangePasswordRequest;
import com.onar.eymen.userservice.model.dto.request.RegisterRequest;
import com.onar.eymen.userservice.model.dto.request.UpdateProfileRequest;
import com.onar.eymen.userservice.model.dto.response.UserResponse;
import com.onar.eymen.userservice.security.domain.UserAuthorizationDomainService;
import com.onar.eymen.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
@Tag(name = "Kullanıcı işlemleri")
public class UserController {
  private final UserService service;
    private final UserAuthorizationDomainService userAuthorizationDomainService;

    @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Yeni bir kullanıcı kaydı oluşturur.")
  @SecurityRequirements()
  public SuccessResponse<UserResponse> registerUser(@RequestBody @Valid RegisterRequest request) {
    return service.registerUser(request);
  }

  @GetMapping("/findAll")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Bütün kullanıcıları getirir.")
  @SecurityRequirement(name = "bearerAuth")
  public SuccessResponse<List<UserResponse>> findAllUsers() {
    return service.findAllUsers();
  }

  @PutMapping("/{id}/update")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("@userAuthorizationDomainService.isCurrentUser(#id)")
  @Operation(summary = "Kullanıcı profilini günceller.")
  @SecurityRequirement(name = "bearerAuth")
  public SuccessResponse<UserResponse> updateUser(
      @PathVariable Long id, @RequestBody @Valid UpdateProfileRequest request) {
    return service.updateProfile(id, request);
  }

  @PutMapping("/{id}/passwordUpdate")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Kullanıcı şifresini günceller.")
  @SecurityRequirement(name = "bearerAuth")
  public SuccessResponse<UserResponse> changePassword(
      @PathVariable Long id, @Valid @RequestBody ChangePasswordRequest request) {
    return service.changePassword(id, request);
  }
}
