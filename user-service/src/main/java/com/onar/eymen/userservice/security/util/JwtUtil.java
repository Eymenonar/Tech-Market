package com.onar.eymen.userservice.security.util;

import static com.onar.eymen.common.core.constant.JwtConstants.*;
import static java.lang.System.currentTimeMillis;

import com.onar.eymen.common.core.advice.exception.auth.InvalidJwtException;
import com.onar.eymen.userservice.model.entity.User;
import com.onar.eymen.userservice.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.*;
import javax.crypto.SecretKey;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private final SecretKey key;
  private final long jwtExpirationInMs;
  private final long refreshExpirationDateInMs;

  public JwtUtil(SecretKey jwtSecretKey, JwtProperties props) {
    this.key = jwtSecretKey;
    this.jwtExpirationInMs = props.accessExpirationMs();
    this.refreshExpirationDateInMs = props.refreshExpirationMs();
  }

  public String generateToken(User user, Collection<? extends GrantedAuthority> authorities) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(ROLES_CLAIM, authorities.stream().map(GrantedAuthority::getAuthority).toList());
    claims.put(TOKEN_TYPE_CLAIM, ACCESS_TOKEN_TYPE);
    claims.put(GIVEN_NAME_CLAIM, user.getFirstName());
    claims.put(SURNAME_CLAIM, user.getLastName());
    claims.put(EMAIL_CLAIM, user.getEmail());

    return buildToken(String.valueOf(user.getId()), claims, jwtExpirationInMs);
  }

  public String generateRefreshToken(Long userId) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(TOKEN_TYPE_CLAIM, REFRESH_TOKEN_TYPE);

    return buildToken(String.valueOf(userId), claims, refreshExpirationDateInMs);
  }

  public void validateToken(String token) {
    try {
      Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
    } catch (JwtException | IllegalArgumentException ex) {
      throw new InvalidJwtException();
    }
  }

  public Long extractUserId(String token) {
    return Long.valueOf(getClaims(token).getSubject());
  }

  public String extractEmail(String token) {
    return getClaims(token).get(EMAIL_CLAIM, String.class);
  }

  public String getTokenType(String token) {
    return getClaims(token).get(TOKEN_TYPE_CLAIM, String.class);
  }

  public Collection<SimpleGrantedAuthority> getAuthorities(String token) {
    return extractRoles(token).stream().map(SimpleGrantedAuthority::new).toList();
  }

  public Date getExpirationDate(String token) {
    return getClaims(token).getExpiration();
  }

  private String buildToken(String subject, Map<String, Object> claims, long expirationTimeInMs) {
    var issuedAt = new Date();
    var expiration = new Date(currentTimeMillis() + expirationTimeInMs);

    return Jwts.builder()
        .claims(claims)
        .subject(subject)
        .issuedAt(issuedAt)
        .expiration(expiration)
        .signWith(key)
        .compact();
  }

  private List<String> extractRoles(String token) {
    List<?> rolesClaim = getClaims(token).get(ROLES_CLAIM, List.class);
    if (rolesClaim == null) return List.of();

    return rolesClaim.stream().map(Object::toString).toList();
  }

  private Claims getClaims(String token) {
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
  }
}
