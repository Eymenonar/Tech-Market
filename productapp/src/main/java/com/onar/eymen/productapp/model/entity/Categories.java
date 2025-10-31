package com.onar.eymen.productapp.model.entity;

import com.onar.eymen.commonjpa.model.entity.BaseEntity;
import jakarta.persistence.*;
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
@SQLRestriction("is_active=true")
@Table(schema = "product", name = "categories")
public class Categories extends BaseEntity {
  @Column(name = "name", length = 100)
  private String name;

  @Column(name = "description", length = 500)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_category_id")
  private Categories parentCategory;

  @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
  private List<Categories> subCategories = new ArrayList<>();
}
