package com.onar.eymen.userservice.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security.jwt")
public record JwtProperties(String secret, long accessExpirationMs, long refreshExpirationMs) {}
