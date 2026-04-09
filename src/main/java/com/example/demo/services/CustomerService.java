package com.example.demo.services;

import com.example.demo.dto.requests.AddCustomerRequest;
import com.example.demo.dto.requests.UpdateCustomerRequest;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
	private final CustomerRepository customerRepository;

	public Customer add(AddCustomerRequest request) {
		var customer = request.mapToEntity();
		return customerRepository.save(customer);
	}

	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer findById(Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(Customer.class, id));
	}

	public Iterable<Order> getCustomerOrders(Long id) {
		var customer = customerRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(Customer.class, id));
		return customer.getOrders();
	}

	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

	public void update(UpdateCustomerRequest request) {
		var customer = findById(request.getId());
		request.populateFields(customer);
		customerRepository.save(customer);
	}
}
