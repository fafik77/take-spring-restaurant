package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
	@Autowired
	OrderRepository orderRepository;

	@GetMapping("/")
	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
	}

}
