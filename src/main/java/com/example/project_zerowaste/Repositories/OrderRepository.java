package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Order;
import com.example.project_zerowaste.Entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserUsername(String username);
    @Override
    void deleteById(Long aLong);
    List<Order> findAllByPack (Optional<Package> pack);
}