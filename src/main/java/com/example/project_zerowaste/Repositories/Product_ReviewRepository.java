package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Product;
import org.springframework.stereotype.Repository;
import com.example.project_zerowaste.Entities.Product_Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Repository
public interface Product_ReviewRepository extends JpaRepository<Product_Review, Long> {
    List<Product_Review> findAllByUserUsername(String user_id);
    List<Product_Review> findAllByProduct(Optional<Product> product);
}