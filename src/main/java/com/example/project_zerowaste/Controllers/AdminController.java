package com.example.project_zerowaste.Controllers;

import com.example.project_zerowaste.Services.UserService;
import com.example.project_zerowaste.Entities.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private UserService userService;
    @GetMapping("")
    public String basic(){
        return "redirect:/admin/users";
    }
    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin-view";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/{id}/edit")
    public String editUser(
            @PathVariable("id") Long id,
            @ModelAttribute("user") @Valid User updatedUser
    ) {
        userService.editUser(id, updatedUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}