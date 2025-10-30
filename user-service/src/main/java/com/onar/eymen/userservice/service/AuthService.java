package com.onar.eymen.userservice.service;

import static com.onar.eymen.common.core.constant.JwtConstants.REFRESH_TOKEN_TYPE;
import static com.onar.eymen.common.core.response.builder.ResponseBuilder.createSuccessResponse;

import com.onar.eymen.common.core.advice.exception.auth.TokenTypeMismatchException;
import com.onar.eymen.common.core.advice.exception.user.UserNotFoundException;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.userservice.model.entity.User;
import com.onar.eymen.userservice.repository.UserRepository;
import com.onar.eymen.userservice.security.model.dto.response.LoginResponse;
import com.onar.eymen.userservice.security.util.JwtUtil;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
  private final JwtUtil jwtUtil;
  private final UserRepository userRepository;
  private final UserDetailsService userDetailService;
  private final AuthenticationManager authenticationManager;

  public SuccessResponse<LoginResponse> login(String email, String password) {
    authenticateUser(email, password);
    var user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    var response = generateAuthTokens(user);

    return createSuccessResponse(response, Messages.Auth.LOGIN_SUCCESS);
  }

  public SuccessResponse<LoginResponse> tokenRefresh(String refreshToken) {
    validateRefreshToken(refreshToken);
    var userId = jwtUtil.extractUserId(refreshToken);
    var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    var authorities = getUserAuthorities(user.getEmail());
    var token = jwtUtil.generateToken(user, authorities);
    var newRefresh = jwtUtil.generateRefreshToken(user.getId());
    var response = new LoginResponse(token, newRefresh);

    return createSuccessResponse(response, Messages.Auth.REFRESH_SUCCESS);
  }

  private void authenticateUser(String username, String password) {
    var authToken = new UsernamePasswordAuthenticationToken(username, password);
    authenticationManager.authenticate(authToken);
  }

  private LoginResponse generateAuthTokens(User user) {
    var authorities = getUserAuthorities(user.getEmail());
    var accessToken = jwtUtil.generateToken(user, authorities);
    var refreshToken = jwtUtil.generateRefreshToken(user.getId());

    return new LoginResponse(accessToken, refreshToken);
  }

  private Collection<? extends GrantedAuthority> getUserAuthorities(String email) {
    return userDetailService.loadUserByUsername(email).getAuthorities();
  }

  private void validateRefreshToken(String refreshToken) {
    jwtUtil.validateToken(refreshToken);
    boolean isInvalidTokenType = !REFRESH_TOKEN_TYPE.equals(jwtUtil.getTokenType(refreshToken));
    if (isInvalidTokenType) throw new TokenTypeMismatchException(refreshToken);
  }
}
