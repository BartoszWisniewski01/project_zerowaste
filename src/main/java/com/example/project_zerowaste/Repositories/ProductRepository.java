package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserUsername(String user_id);
}

