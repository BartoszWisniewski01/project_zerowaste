package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Notification;
import com.example.project_zerowaste.Repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> findAll(String name) {
        return findAllByUserUsernameAndDate(name, LocalDate.now());
    }

    private List<Notification> findAllByUserUsernameAndDate(String username, LocalDate date) {
        return notificationRepository.findAllByUserUsernameAndDate(username, date);
    }
}