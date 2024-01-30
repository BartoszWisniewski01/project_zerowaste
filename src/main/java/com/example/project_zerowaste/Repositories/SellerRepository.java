package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Override
    void deleteById(Long aLong);
}