package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Notification;
import com.example.project_zerowaste.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    public List<Notification> findAll(String name) {
        LocalDate today = LocalDate.now();
        List<Notification> notifications = notificationRepository.findAllByUserUsernameAndDate(name, today);
        return notifications;
    }
}