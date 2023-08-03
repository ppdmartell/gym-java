package com.example.demo;

import com.example.demo.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {

    private final String name;
    private final Address address;

    @Autowired
    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
