package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "Packages")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Setter

public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "expiry_date")
    private Date expiry_date;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pack")
    private List<Product_Package> product_package;
}