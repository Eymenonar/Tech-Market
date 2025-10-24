package com.onar.eymen.productapp.model.entity;

import com.onar.eymen.commonjpa.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("is_active=true")
@Table(name = "products", schema = "product")
public class Products extends BaseEntity {
  @Column(name = "name", nullable = false, length = 200)
  private String name;

  @Column(name = "description", length = 5000)
  private String description;

  @Column(name = "sku", length = 80) // İş tarafının gördüğü ürün kodu, bağımsızdır.
  private String sku;

  @Column(name = "price", nullable = false, precision = 12, scale = 2)
  private BigDecimal price;

  @Column(name = "stock_qty", nullable = false)
  private Integer stockQty = 0;
}
