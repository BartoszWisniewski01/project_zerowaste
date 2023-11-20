package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "Sellers")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter

public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}