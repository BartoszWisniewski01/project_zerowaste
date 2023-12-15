package com.example.project_zerowaste.Controllers;

import com.example.project_zerowaste.Entities.Notification;
import com.example.project_zerowaste.Services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {
    private NotificationService notificationService;
    @GetMapping("/all")
    public String getAll(
            Model model,
            Principal principal
    ) {
        List<Notification> notifications = notificationService.findAll(principal.getName());

        model.addAttribute("notifications", notifications);
        return "mainpage";
    }
}