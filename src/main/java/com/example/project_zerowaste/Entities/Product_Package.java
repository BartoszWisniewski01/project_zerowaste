package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "Product_Package")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter

public class Product_Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package pack;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "quantity")
    private int quantity;
}