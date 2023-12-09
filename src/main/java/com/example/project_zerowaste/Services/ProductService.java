package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Package;
import com.example.project_zerowaste.Entities.Product;
import com.example.project_zerowaste.Entities.Product_Package;
import com.example.project_zerowaste.Repositories.PackageRepository;
import com.example.project_zerowaste.Repositories.ProductRepository;
import com.example.project_zerowaste.Repositories.Product_PackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private PackageRepository packageRepository;
    private Product_PackageRepository productPackageRepository;
    private UserService userService;

    public void save(Product product, String username) {
        product.setUser(userService.findByUsername(username));
        productRepository.save(product);
    }

    public List<Product> findAll(String username) {
        return productRepository.findAllByUserUsername(username);
    }

    public void deleteProductById(Long id) {
        List<Product_Package> productPackages = productPackageRepository.findAllByProduct_Id(id);
        for (Product_Package productPackage : productPackages) {
            Package pack = productPackage.getPack();
            productPackageRepository.deleteAll(pack.getProduct_package());
            packageRepository.delete(pack);
        }
        productRepository.deleteById(id);
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