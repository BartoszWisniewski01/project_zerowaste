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
    public String addOrderForm(Model model, Principal principal) {
        model.addAttribute("order", new Order());
        model.addAttribute("packages", packageService.findAll());
        return "order-form";
    }

    @PostMapping("/add")
    public String addOrder(
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
    public String editOrderForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        model.addAttribute("packages", packageService.findAll(principal.getName()));
        return "edit-order-form";
    }

    @PostMapping("/{id}/edit")
    public String editOrder(
            @PathVariable("id") Long id,
            @ModelAttribute("order") @Valid Order updatedOrder,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "edit-order-form";
        }
        else {
            orderService.editOrder(id, updatedOrder);
            return "redirect:/orders/all";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return "redirect:/orders/all";
    }
}