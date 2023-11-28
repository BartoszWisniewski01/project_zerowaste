package com.example.project_zerowaste.Controllers;

import com.example.project_zerowaste.Services.UserService;
import com.example.project_zerowaste.Entities.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private UserService userService;
    @GetMapping("")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping("")
    public String registrationProcess(
            @ModelAttribute ("user") @Valid User user,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "registration-form";
        }
        else {
            userService.save(user);
            return "redirect:/admin/users";
        }
    }
}