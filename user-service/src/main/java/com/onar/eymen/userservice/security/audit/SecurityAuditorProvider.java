package com.onar.eymen.userservice.security.audit;

import com.onar.eymen.commonjpa.audit.AuditorProvider;
import com.onar.eymen.commonjpa.constant.Auditor;
import com.onar.eymen.userservice.security.model.dto.CustomUserDetails;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SecurityAuditorProvider implements AuditorProvider {

  @Override
  public Long getCurrentAuditor() {
    Authentication authentication = getAuthentication();
    if (isAuthenticated(authentication)) return extractUserId(authentication);

    return Auditor.SYSTEM;
  }

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  private static boolean isAuthenticated(Authentication authentication) {
    return authentication != null
        && authentication.isAuthenticated()
        && !(authentication instanceof AnonymousAuthenticationToken);
  }

  private Long extractUserId(Authentication authentication) {
    Object principal = authentication.getPrincipal();
    if (principal instanceof CustomUserDetails userDetails) return userDetails.getId();

    return Auditor.SYSTEM;
  }
}
