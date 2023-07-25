package com.example.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.address.entity.Address;

public interface IAddressRepository extends JpaRepository<Address, Long> {

}
