package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserUsername(String username);
    @Override
    void deleteById(Long aLong);
}