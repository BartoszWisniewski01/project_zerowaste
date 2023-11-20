package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "Seller_Reviews")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter

public class Seller_Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @Column(name = "rating")
    private double rating;
    @Column(name = "comment")
    private String comment;
}