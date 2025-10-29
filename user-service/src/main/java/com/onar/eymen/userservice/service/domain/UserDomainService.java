package com.onar.eymen.userservice.service.domain;

import com.onar.eymen.userservice.model.dto.request.RegisterRequest;
import com.onar.eymen.userservice.model.entity.Role;
import com.onar.eymen.userservice.model.entity.User;
import com.onar.eymen.userservice.model.enums.RoleType;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDomainService {
  private final PasswordEncoder passwordEncoder;

  public String encodePassword(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

  public static boolean hasAdminRole(User user) {
    return user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleType.ROLE_ADMIN));
  }

  public User buildUser(RegisterRequest request, Set<Role> roles) {
    return User.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .roles(roles)
        .build();
  }
}
