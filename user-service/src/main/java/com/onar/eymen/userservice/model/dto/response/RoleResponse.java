package com.onar.eymen.userservice.model.dto.response;

import com.onar.eymen.userservice.model.entity.Role;
import java.util.Set;
import java.util.stream.Collectors;

public record RoleResponse(Long id, String name) {
  public static RoleResponse from(Role role) {
    return new RoleResponse(role.getId(), role.getName().name());
  }

  public static Set<RoleResponse> from(Set<Role> roles) {
    return roles.stream().map(RoleResponse::from).collect(Collectors.toSet());
  }
}
