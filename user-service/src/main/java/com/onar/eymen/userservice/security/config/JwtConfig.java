package com.onar.eymen.userservice.security.config;

import com.onar.eymen.userservice.security.properties.JwtProperties;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {
  @Bean
  SecretKey jwtSecretKey(JwtProperties props) {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(props.secret()));
  }
}
