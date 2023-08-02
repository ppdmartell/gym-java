package com.example.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.address.entity.Address;
import com.example.address.service.IAddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	IAddressService addressService;
	
	@PostMapping("/create")
	public HttpStatus createAddress(@RequestBody Address address) {
		addressService.save(address);
		return HttpStatus.OK;
	}
	
	@GetMapping("/retrieve")
	public ResponseEntity<List<Address>> retrieveAllAddresses() {
		return ResponseEntity.ok()
							 .body(addressService.getAllAddresses());
	}
	
	@GetMapping("/retrieve/{id}")
	public ResponseEntity<Address> retrieveById(@PathVariable Long id) {
		return ResponseEntity.ok()
				             .body(addressService.getAddressById(id));
	}
	
	@PutMapping("/update")
	public HttpStatus updateAddress(@RequestBody Address address) {
		addressService.update(address);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/delete/{id}")
	public HttpStatus deleteById(@PathVariable Long id) {
		addressService.deleteById(id);
		return HttpStatus.OK;
	}
}
