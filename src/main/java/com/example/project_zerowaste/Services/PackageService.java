package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Product_Package;
import com.example.project_zerowaste.Entities.User;
import com.example.project_zerowaste.Repositories.PackageRepository;
import com.example.project_zerowaste.Entities.Package;
import com.example.project_zerowaste.Repositories.Product_PackageRepository;
import com.example.project_zerowaste.Repositories.User_SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PackageService {
    private PackageRepository packageRepository;
    private Product_PackageRepository productPackageRepository;
    private User_SellerRepository userSellerRepository;
    private UserService userService;

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

    public void deletePackageById(Long id) {
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