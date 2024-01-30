package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table (name = "Products")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    private List<Product_Package> product_package;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}