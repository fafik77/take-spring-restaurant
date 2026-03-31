package com.example.demo.controllers;

import com.example.demo.dto.requests.AddCustomerRequest;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/")
	public Iterable<Customer> getAll() {
		return customerRepository.findAll();
	}

	@GetMapping("/{id}")
	public Customer getById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
	}

	@GetMapping("/{id}/orders")
	public Iterable<Order> getCustomerOrders(Long id) {
		var customer = customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
		return customer.getOrders();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer add(@RequestBody AddCustomerRequest request) {
		var customer = Customer.builder().deliveryAddress(request.getDeliveryAddress()).phoneNumber(request.getPhoneNumber()).build();
		return customerRepository.save(customer);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Customer customer) {
		if (customerRepository.existsById(customer.getId())) {
			customerRepository.save(customer);
			return;
		}
		throw new NotFoundException(customer.getId());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		customerRepository.deleteById(id);
	}


}
