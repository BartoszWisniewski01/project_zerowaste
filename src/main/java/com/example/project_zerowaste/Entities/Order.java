package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table (name = "Orders")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "status")
    private String status;
    @Column(name = "order_date")
    private Date order_date;
}
