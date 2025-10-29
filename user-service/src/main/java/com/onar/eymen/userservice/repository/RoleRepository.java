package com.onar.eymen.userservice.repository;

import com.onar.eymen.userservice.model.entity.Role;
import com.onar.eymen.userservice.model.enums.RoleType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleType name);
}
