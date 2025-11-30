package com.onar.eymen.orderservice.model.enums;

public enum OrderStatus {
  PENDING, // Beklemede
  CONFIRMED, // Onaylandı
  PROCESSING, // İşleniyor
  SHIPPED, // Kargoya verildi
  DELIVERED, // Teslim edildi
  COMPLETED, // Tamamlandı
  CANCELLED // İptal edildi
}
