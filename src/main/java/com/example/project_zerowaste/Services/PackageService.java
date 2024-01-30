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
public class PackageService {
    private final PackageRepository packageRepository;
    private final Product_PackageRepository productPackageRepository;
    private final User_SellerRepository userSellerRepository;
    private final TicketRepository ticketRepository;
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;
    private final UserService userService;

    public void save(Package pack, String username) {
        User user = userService.findByUsername(username);
        pack.setUser(user);
        pack.setSeller(userSellerRepository.findSellerByUser(user));
        packageRepository.save(pack);

        Product_Package productPackage = Product_Package.builder()
                .product(pack.getProduct())
                .pack(pack)
                .quantity(1)
                .build();
        productPackageRepository.save(productPackage);
    }

    public List<Package> findAll(String username) {
        return packageRepository.findAllByUserUsername(username);
    }
    public List<Package> findAll() {
        return packageRepository.findAll();
    }
    public void deletePackageById(Long id) {
        List<Order> orders = orderRepository.findAllByPack(Optional.ofNullable(packageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package ID: " + id))));
        ProductService.deleteTicketsNotifications(orders, notificationRepository, ticketRepository, orderRepository);
        packageRepository.deleteById(id);
    }

    public Package findById(Long id){
        return packageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package ID: " + id));
    }

    public void editPackage(Long id, Package updatedPackage) {
        Package pack = packageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package ID: " + id));
        Product_Package productPackage = productPackageRepository.findByPackage(pack);

        pack.setName(updatedPackage.getName());
        pack.setExpiry_date(updatedPackage.getExpiry_date());
        pack.setProduct(updatedPackage.getProduct());
        packageRepository.save(pack);

        productPackage.setProduct(pack.getProduct());
        productPackageRepository.save(productPackage);
    }
}
