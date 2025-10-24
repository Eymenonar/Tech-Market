package com.onar.eymen.commonjpa.audit;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<Long> {

  private final AuditorProvider auditorProvider;

  @Override
  public Optional<Long> getCurrentAuditor() {
    return Optional.ofNullable(auditorProvider.getCurrentAuditor());
  }
}
