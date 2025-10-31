package com.onar.eymen.commonjpa.audit.config;

import com.onar.eymen.commonjpa.audit.AuditorProvider;
import com.onar.eymen.commonjpa.constant.Auditor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditorProviderConfig {
  @Bean
  @ConditionalOnMissingBean(AuditorProvider.class)
  public AuditorProvider defaultAuditorProvider() {
    return () -> Auditor.SYSTEM;
  }
}
