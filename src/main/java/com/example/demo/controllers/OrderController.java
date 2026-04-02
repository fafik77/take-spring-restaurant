package com.example.demo.controllers;

import com.example.demo.dto.requests.AddOrderRequest;
import com.example.demo.entities.Order;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/")
	public Iterable<Order> getAll() {
		return orderRepository.findAll();
	}

	@GetMapping("/{id}")
	public Order getById(@PathVariable Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Order add(@RequestBody AddOrderRequest request) {
		var customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new NotFoundException(request.getCustomerId()));
		var order = Order.builder().delivery(request.isDelivery()).takeout(request.isTakeout()).selfTakeout(request.isSelfTakeout())
				.customer(customer).build();
		return orderRepository.save(order);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		orderRepository.deleteById(id);
	}

}
