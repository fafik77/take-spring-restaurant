package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/")
	public Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	@GetMapping("/{id}")
	public Customer getCustomerById(long id) {
		return customerRepository.findById(id).get();
	}
}
