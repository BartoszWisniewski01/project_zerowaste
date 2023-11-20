package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table (name = "Notifications")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private Date date;
}