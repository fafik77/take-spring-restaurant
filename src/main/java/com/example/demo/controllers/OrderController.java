package com.example.demo.controllers;

import com.example.demo.models.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
