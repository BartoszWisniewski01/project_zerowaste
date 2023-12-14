package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Order;
import com.example.project_zerowaste.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Override
    void deleteById(Long aLong);
    List<Ticket> findAllByUserUsername(String username);
    List<Ticket> findAllByOrder(Order order);
}