package com.example.project_zerowaste.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    @GetMapping
    public String showLogin() {
        return "login-form";
    }
}