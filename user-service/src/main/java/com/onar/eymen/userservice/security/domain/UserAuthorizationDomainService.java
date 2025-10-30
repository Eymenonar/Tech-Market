package com.onar.eymen.userservice.security.domain;

import com.onar.eymen.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthorizationDomainService {
  private final UserService userService;

  public boolean isCurrentUser(Long id) {
    var context = SecurityContextHolder.getContext();
    var currentEmail = context.getAuthentication().getName();
    var user = userService.getUserByEmail(currentEmail);
    return user.getData().id().equals(id);
  }
}
