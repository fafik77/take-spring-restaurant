package com.example.demo.controllers;

import com.example.demo.dto.requests.AddCustomerRequest;
import com.example.demo.dto.requests.UpdateCustomerRequest;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.services.CustomerService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {
	private final CustomerService customerService;

	@GetMapping("/")
	public Iterable<Customer> getAll() {
		return customerService.findAll();
	}

	@GetMapping("/{id}")
	public Customer getById(Long id) {
		return customerService.findById(id);
	}

	@GetMapping("/{id}/orders")
	public Iterable<Order> getCustomerOrders(Long id) {
		return customerService.getCustomerOrders(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer add(@RequestBody @Valid @NonNull AddCustomerRequest request) {
		return customerService.add(request);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody @Valid @NonNull UpdateCustomerRequest request) {
		customerService.update(request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		customerService.deleteById(id);
	}


}
