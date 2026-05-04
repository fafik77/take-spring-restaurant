package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;

	public Iterable<Order> findAll() {
		return orderRepository.findAll();
	}

	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}
}
