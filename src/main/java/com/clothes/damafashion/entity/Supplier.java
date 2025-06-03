package com.clothes.damafashion.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "suppliers")
public class Supplier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private String contact;

  @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> products;

  public Supplier(String name, String contact, List<Product> products) {
    this.name = name;
    this.contact = contact;
    this.products = products;
  }

  public Supplier() {

  }
}