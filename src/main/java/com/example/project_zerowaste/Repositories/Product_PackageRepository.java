package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Product_Package;
import com.example.project_zerowaste.Entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Product_PackageRepository extends JpaRepository<Product_Package, Long> {
    @Override
    void deleteById(Long aLong);
    @Query("SELECT pp FROM Product_Package pp WHERE pp.pack = :pack")
    Product_Package findByPackage(@Param("pack") Package pack);
    List<Product_Package> findAllByProduct_Id(Long id);
}