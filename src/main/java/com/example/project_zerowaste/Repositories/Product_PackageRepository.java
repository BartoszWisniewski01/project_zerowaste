package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Product_Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Product_PackageRepository extends JpaRepository<Product_Package, Long> {
    @Override
    void deleteById(Long aLong);
}