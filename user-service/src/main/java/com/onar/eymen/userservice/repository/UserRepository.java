package com.onar.eymen.userservice.repository;

import com.onar.eymen.commonjpa.repository.BaseRepository;
import com.onar.eymen.userservice.model.entity.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);
}
