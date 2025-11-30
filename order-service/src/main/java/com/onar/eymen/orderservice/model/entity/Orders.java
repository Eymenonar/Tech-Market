package com.onar.eymen.orderservice.model.entity;

import com.onar.eymen.commonjpa.model.entity.BaseEntity;
import com.onar.eymen.orderservice.model.enums.OrderStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLRestriction("is_Active=true")
@Table(schema = "product", name = "orders")
public class Orders extends BaseEntity {
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "order_number", nullable = false, unique = true, length = 50)
  private String orderNumber;

  @Column(name = "order_status", nullable = false, length = 20)
  private OrderStatus orderStatus = OrderStatus.PENDING;

  @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
  private BigDecimal totalAmount;

  @Column(name = "shipping_address", nullable = false, length = 500)
  private String shippingAddress;

  @Column(name = "notes", columnDefinition = "TEXT")
  private String notes;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItems = new ArrayList<>();
}
