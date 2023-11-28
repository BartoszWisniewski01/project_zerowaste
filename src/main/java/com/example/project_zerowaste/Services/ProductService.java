package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Product;
import com.example.project_zerowaste.Repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private UserService userService;

    public void save(Product product, String username) {
        product.setUser(userService.findByUsername(username));
        productRepository.save(product);
    }

    public List<Product> findAll(String username) {
        return productRepository.findAllByUserUsername(username);
    }

    public void deleteProductById(Long id) {
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