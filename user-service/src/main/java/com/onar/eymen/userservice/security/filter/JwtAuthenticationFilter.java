package com.onar.eymen.userservice.security.filter;

import static com.onar.eymen.common.core.constant.JwtConstants.ACCESS_TOKEN_TYPE;

import com.onar.eymen.common.core.advice.exception.auth.TokenTypeMismatchException;
import com.onar.eymen.userservice.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  public static final String BEARER_PREFIX = "Bearer ";
  public static final String AUTHORIZATION_HEADER = "Authorization";

  private final JwtUtil jwtUtil;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    try {
      var token = getBearerTokenFromRequest(request);
      if (token != null) {
        authenticateRequestWithToken(token, request);
      }
    } catch (Exception ex) {
    }
    filterChain.doFilter(request, response);
  }

  private String getBearerTokenFromRequest(HttpServletRequest request) {
    var authHeader = request.getHeader(AUTHORIZATION_HEADER);
    var isValidBearerToken =
        StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER_PREFIX);
    return isValidBearerToken ? authHeader.substring(BEARER_PREFIX.length()) : null;
  }

  private void validateAccessToken(String token) {
    jwtUtil.validateToken(token);
    var tokenType = jwtUtil.getTokenType(token);
    if (!ACCESS_TOKEN_TYPE.equals(tokenType)) throw new TokenTypeMismatchException();
  }

  private void authenticateRequestWithToken(String token, HttpServletRequest request) {
    if (SecurityContextHolder.getContext().getAuthentication() != null) return;
    validateAccessToken(token);
    var email = jwtUtil.extractEmail(token);
    var userDetails = userDetailsService.loadUserByUsername(email);
    var authToken =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authToken);
  }
}
