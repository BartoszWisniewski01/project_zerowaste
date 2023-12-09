package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiry_date;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pack", orphanRemoval = true)
    private List<Product_Package> product_package;
}