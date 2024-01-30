package com.example.project_zerowaste.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.project_zerowaste.Entities.Package;
import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findAllByUserUsername(String username);
    @Override
    void deleteById(Long aLong);
}