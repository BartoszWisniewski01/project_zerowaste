package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Order;
import com.example.project_zerowaste.Entities.Ticket;
import com.example.project_zerowaste.Entities.User;
import com.example.project_zerowaste.Repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final OrderService orderService;

    public void save(Ticket ticket,Long id, String username) {
        User user = userService.findByUsername(username);
        Order order = orderService.findById(id);
        ticket.setOrder(order);
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }

    public List<Ticket> findAll(String username) {
        return ticketRepository.findAllByUserUsername(username);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }
}