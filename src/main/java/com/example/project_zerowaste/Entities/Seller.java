package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table (name = "Sellers")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Builder
@Setter

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
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private List<User_Seller> user_seller;
}