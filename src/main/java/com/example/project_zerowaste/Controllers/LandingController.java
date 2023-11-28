package com.example.project_zerowaste.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
    @GetMapping("/landingpage")
    public String welcome() {
        return "landingpage";
    }
}