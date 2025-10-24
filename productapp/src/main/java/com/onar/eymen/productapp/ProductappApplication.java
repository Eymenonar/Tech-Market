package com.onar.eymen.productapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.onar.eymen")
public class ProductappApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductappApplication.class, args);
  }
}
