package com.clothes.damafashion.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double price;

  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "supplier_id", nullable = false)
  private Supplier supplier;

  @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private Stock stock;
}
