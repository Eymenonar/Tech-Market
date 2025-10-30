package com.onar.eymen.userservice.service;

import com.onar.eymen.userservice.model.entity.User;
import com.onar.eymen.userservice.repository.UserRepository;
import com.onar.eymen.userservice.security.model.dto.CustomUserDetails;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
  private final UserRepository repository;

  private List<SimpleGrantedAuthority> mapRoleToAuthority(User user) {
    return user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .toList();
  }

  private UserDetails buildUserDetails(User user) {
    return new CustomUserDetails(
        user.getId(),
        user.getEmail(),
        user.getPassword(),
        user.getAudit().isActive(),
        mapRoleToAuthority(user));
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var message = String.format("Bu emailda bir kullanıcı bulunamadı '%s'", email);
    var user =
        repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(message));

    return buildUserDetails(user);
  }
}
