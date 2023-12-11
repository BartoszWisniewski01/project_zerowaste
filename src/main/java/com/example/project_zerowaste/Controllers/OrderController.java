package com.example.project_zerowaste.Controllers;

import com.example.project_zerowaste.Configuration.GlobalException;
import com.example.project_zerowaste.Entities.Order;
import com.example.project_zerowaste.Services.OrderService;
import com.example.project_zerowaste.Services.PackageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor

public class OrderController {
    private OrderService orderService;
    private PackageService packageService;

    @GetMapping("/all")
    public String getAll(
            Model model,
            Principal principal
    ) {
        List<Order> orders = orderService.findAll(principal.getName());

        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/add")
    public String addPackageForm(Model model, Principal principal) {
        model.addAttribute("order", new Order());
        model.addAttribute("packages", packageService.findAll(principal.getName()));
        return "order-form";
    }

    @PostMapping("/add")
    public String addPackage(
            @ModelAttribute ("order") @Valid Order order,
            BindingResult bindingResult,
            Model model,
            Principal principal
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "order-form";
        }
        else {
            try {
                orderService.save(order, principal.getName());
            } catch (GlobalException exception) {
                model.addAttribute("errorMessage", exception.getMessage());
                return "order-form";
            }
            return "redirect:/orders/all";
        }
    }

    @GetMapping("/{id}/edit")
    public String editPackageForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        model.addAttribute("packages", packageService.findAll(principal.getName()));
        return "edit-order-form";
    }

    @PostMapping("/{id}/edit")
    public String editPackage(
            @PathVariable("id") Long id,
            @ModelAttribute("order") @Valid Order updatedOrder,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "edit-order-form";
        }
        else {
            orderService.editPackage(id, updatedOrder);
            return "redirect:/orders/all";
        }
    }

    @GetMapping("/{id}/delete")
    public String deletePackage(@PathVariable("id") Long id) {
        orderService.deletePackageById(id);
        return "redirect:/orders/all";
    }
}