package com.example.address.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.address.entity.Address;
import com.example.address.exception.ResourceNotFoundException;
import com.example.address.repository.IAddressRepository;
import com.example.address.service.IAddressService;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private IAddressRepository addressRepository;

	@Override
	@Transactional
	public void save(Address address) {
		addressRepository.saveAndFlush(address);
	}

	@Override
	@Transactional(readOnly = true)
	public Address getAddressById(Long id) {
		Optional<Address> address = addressRepository.findById(id);
		if(address.isPresent()) return address.get();
		else throw new ResourceNotFoundException("Custom error message: An address with ID: "
													+ id
													+ " was not found.");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@Override
	@Transactional
	public void update(Address address) {
		if(address.getId() == null) throw new ResourceNotFoundException("ID is not present.");
		if(!addressRepository.existsById(address.getId())) throw new ResourceNotFoundException(
													"Custom error message: An address with ID: "
													+ address.getId()
													+ " was not found.");
		
		addressRepository.save(address);
		
	}

	@Override
	@Transactional
	public void delete(Address address) {
		Optional<Address> foundAddress = addressRepository.findById(address.getId());
		if(!foundAddress.isPresent()) throw new ResourceNotFoundException(
				"Custom error message: An address with ID: "
				+ address.getId()
				+ " was not found.");
		addressRepository.delete(address);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Optional<Address> foundAddress = addressRepository.findById(id);
		if(!foundAddress.isPresent()) throw new ResourceNotFoundException(
				"Custom error message: An address with ID: "
				+ id
				+ " was not found.");
		
		addressRepository.deleteById(id);
	}

}
