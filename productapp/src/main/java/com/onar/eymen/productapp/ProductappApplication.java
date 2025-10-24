package com.onar.eymen.productapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.onar.eymen")
@EnableJpaAuditing
public class ProductappApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductappApplication.class, args);
  }
}
