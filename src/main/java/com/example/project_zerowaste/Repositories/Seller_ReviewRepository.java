package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Seller;
import com.example.project_zerowaste.Entities.Seller_Review;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Repository
public interface Seller_ReviewRepository extends JpaRepository<Seller_Review, Long> {
    List<Seller_Review> findAllByUserUsername(String user_id);
    List<Seller_Review> findAllBySeller(Optional<Seller> seller);
}