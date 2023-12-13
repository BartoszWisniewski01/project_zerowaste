package com.example.project_zerowaste.Controllers;

import com.example.project_zerowaste.Configuration.GlobalException;
import com.example.project_zerowaste.Entities.Ticket;
import com.example.project_zerowaste.Services.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/tickets")
@AllArgsConstructor

public class TicketController {
    private TicketService ticketService;

    @GetMapping("/all")
    public String getAll(
            Model model,
            Principal principal
    ) {
        List<Ticket> tickets = ticketService.findAll(principal.getName());

        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/{id}/add")
    public String addTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticket-form";
    }

    @PostMapping("/{id}/add")
    public String addTicket(
            @PathVariable("id") Long id,
            @ModelAttribute ("ticket")
            @Valid Ticket ticket,
            BindingResult bindingResult,
            Model model,
            Principal principal
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "ticket-form";
        }
        else {
            try {
                ticketService.save(ticket,id, principal.getName());
            } catch (GlobalException exception) {
                model.addAttribute("errorMessage", exception.getMessage());
                return "ticket-form";
            }
            return "redirect:/orders/all";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteTicket(@PathVariable("id") Long id) {
        ticketService.deleteTicketById(id);
        return "redirect:/tickets/all";
    }
}