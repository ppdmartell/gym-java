package com.example.address.service;

import com.example.address.entity.Address;

import java.util.List;

public interface IAddressService {
	
	void save(Address address);
	Address getAddressById(Long id);
	List<Address> getAllAddresses();
	void update(Address address);
	void delete(Address address);
	void deleteById(Long id);
	
	
}
