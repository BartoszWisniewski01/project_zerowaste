package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "Product_Reviews")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Product_Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "rating")
    private double rating;
    @Column(name = "comment")
    private String comment;
}