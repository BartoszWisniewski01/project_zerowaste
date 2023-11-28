package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    @Override
    void deleteById(Long aLong);
}

