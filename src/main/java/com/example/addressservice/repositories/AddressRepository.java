package com.example.addressservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.addressservice.models.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>{

    List<Address> findByStudentId(Long studentId);
}
