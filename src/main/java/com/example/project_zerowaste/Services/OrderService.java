package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Order;
import com.example.project_zerowaste.Entities.User;
import com.example.project_zerowaste.Repositories.OrderRepository;
import com.example.project_zerowaste.Repositories.User_SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private User_SellerRepository userSellerRepository;
    private UserService userService;

    public void save(Order order, String username) {
        User user = userService.findByUsername(username);
        order.setUser(user);
        order.setSeller(userSellerRepository.findSellerByUser(user));
        orderRepository.save(order);
    }

    public List<Order> findAll(String username) {
        return orderRepository.findAllByUserUsername(username);
    }

    public void deletePackageById(Long id) {
        orderRepository.deleteById(id);
    }

    public Order findById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + id));
    }

    public void editPackage(Long id, Order updatedOrder) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package ID: " + id));

        order.setStatus(updatedOrder.getStatus());
        order.setOrder_date(updatedOrder.getOrder_date());
        order.setAddress(updatedOrder.getAddress());
        orderRepository.save(order);
    }
}