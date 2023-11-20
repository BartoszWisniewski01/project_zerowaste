package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "Addresses")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "street_name")
    private String street_name;
    @Column(name = "building_number")
    private int building_number;
    @Column(name = "apartment_number")
    private int apartment_number;
    @Column(name = "postcode")
    private int postcode;
}
