package com.unir.products.model.pojo;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "country")
  private String country;

  @Column(name = "description")
  private String description;

  @Column(name = "visible")
  private String visible;


}
