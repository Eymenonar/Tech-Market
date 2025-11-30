package com.onar.eymen.orderservice.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items", schema = "product")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Orders order;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "product_name", nullable = false, length = 200)
  private String productName;

  @Column(name = "product_sku", length = 80)
  private String productSku;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Column(name = "unit_price", nullable = false, precision = 12, scale = 2)
  private BigDecimal unitPrice;

  @Column(name = "subtotal", nullable = false, precision = 12, scale = 2)
  private BigDecimal subtotal;

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_by")
  private Long updatedBy;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @PrePersist
  public void calculateSubtotal() {
    if (this.quantity != null && this.unitPrice != null) {
      this.subtotal = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  public void updateTimestamp() {
    this.updatedAt = LocalDateTime.now();
  }
}
