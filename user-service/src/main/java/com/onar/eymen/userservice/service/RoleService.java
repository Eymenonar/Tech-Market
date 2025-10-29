package com.onar.eymen.userservice.service;

import com.onar.eymen.common.core.advice.exception.role.RoleNotFoundException;
import com.onar.eymen.userservice.model.entity.Role;
import com.onar.eymen.userservice.model.enums.RoleType;
import com.onar.eymen.userservice.repository.RoleRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleService {
  private final RoleRepository repository;

  public Set<Role> assignDefaultRoles() {
    return Set.of(
        repository.findByName(RoleType.ROLE_USER).orElseThrow(RoleNotFoundException::new));
  }
}
