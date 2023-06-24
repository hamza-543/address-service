package com.example.addressservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.addressservice.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
  
}
