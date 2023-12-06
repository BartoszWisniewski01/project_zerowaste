package com.example.project_zerowaste.Repositories;

import com.example.project_zerowaste.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address , Long> {
    @Override
    void deleteById(Long aLong);
    Address getById(int address_id);
}