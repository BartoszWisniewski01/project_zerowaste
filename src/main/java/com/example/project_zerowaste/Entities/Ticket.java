package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "Tickets")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "title")
    private String title;
    @Column(name = "comment")
    private String comment;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

