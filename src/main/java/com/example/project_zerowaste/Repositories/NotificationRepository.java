package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Notification;
import com.example.project_zerowaste.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByOrder(Optional<Order> byId);
    List<Notification> findAllByUserUsername(String name);
    @Query("SELECT n FROM Notification n WHERE n.user.username = :username AND DATE(n.date) = :today")
    List<Notification> findAllByUserUsernameAndDate(@Param("username") String username, @Param("today") LocalDate today);
}