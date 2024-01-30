package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.*;
import com.example.project_zerowaste.Entities.Package;
import com.example.project_zerowaste.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final PackageRepository packageRepository;
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final Product_PackageRepository productPackageRepository;
    private final Product_ReviewRepository productReviewRepository;
    private final NotificationRepository notificationRepository;
    private final UserService userService;

    public void save(Product product, String username) {
        product.setUser(userService.findByUsername(username));
        productRepository.save(product);
    }
    public List<Product> findAll(String username) {
        return productRepository.findAllByUserUsername(username);
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public void deleteProductById(Long id) {
        List<Product_Package> productPackages = productPackageRepository.findAllByProduct_Id(id);
        for (Product_Package productPackage : productPackages) {
            Package pack = productPackage.getPack();
            List<Order> orders = orderRepository.findAllByPack(Optional.ofNullable(pack));
            deleteTicketsNotifications(orders, notificationRepository, ticketRepository, orderRepository);
            assert pack != null;
            productPackageRepository.deleteAll(pack.getProduct_package());
            packageRepository.delete(pack);
        }
        Optional<Product> product = productRepository.findById(id);
        List <Product_Review> productReviewList = productReviewRepository.findAllByProduct(product);
        productReviewRepository.deleteAll(productReviewList);
        productRepository.deleteById(id);
    }

    static void deleteTicketsNotifications(List<Order> orders, NotificationRepository notificationRepository, TicketRepository ticketRepository, OrderRepository orderRepository) {
        for (Order order : orders) {
            List<Notification> notifications = notificationRepository.findAllByOrder(Optional.ofNullable(order));
            notificationRepository.deleteAll(notifications);
            List<Ticket> tickets = ticketRepository.findAllByOrder(order);
            ticketRepository.deleteAll(tickets);
        }
        orderRepository.deleteAll(orders);
    }

    public Product findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
    }

    public void editProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());

        productRepository.save(product);
    }
}