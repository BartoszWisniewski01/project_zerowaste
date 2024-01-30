package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Notification;
import com.example.project_zerowaste.Entities.Order;
import com.example.project_zerowaste.Entities.User;
import com.example.project_zerowaste.Repositories.NotificationRepository;
import com.example.project_zerowaste.Repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private NotificationRepository notificationRepository;
    private UserService userService;

    public void save(Order order, String username) {
        User user = userService.findByUsername(username);
        order.setUser(user);
        order.setStatus("ACTIVE");
        orderRepository.save(order);
        Notification notification = Notification.builder()
                .order(order)
                .user(user)
                .title("Hello " + user.getName() + ". Remember about your order.")
                .date(order.getOrder_date())
                .content("Your order number "+ order.getId() + " is ready to be picked up from " + order.getPack().getSeller().getName())
                .build();
        notificationRepository.save(notification);
    }

    public List<Order> findAll(String username) {
        return orderRepository.findAllByUserUsername(username);
    }
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    public void deleteOrderById(Long id) {
        List<Notification> notifications = notificationRepository.findAllByOrder(orderRepository.findById(id));
        notificationRepository.deleteAll(notifications);
        orderRepository.deleteById(id);
    }

    public Order findById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + id));
    }

    public void editOrder(Long id, Order updatedOrder) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package ID: " + id));

        order.setStatus(updatedOrder.getStatus());
        order.setOrder_date(updatedOrder.getOrder_date());
        order.setAddress(updatedOrder.getAddress());
        orderRepository.save(order);
    }
}