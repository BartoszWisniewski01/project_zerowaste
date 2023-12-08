package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Seller;
import com.example.project_zerowaste.Entities.User;
import com.example.project_zerowaste.Entities.User_Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface User_SellerRepository extends JpaRepository<User_Seller, Long> {
    @Override
    void deleteById(Long aLong);
    @Query("SELECT us.seller FROM User_Seller us WHERE us.user = :user")
    Seller findSellerByUser(@Param("user") User user);
}

