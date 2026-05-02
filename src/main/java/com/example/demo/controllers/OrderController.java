package com.example.demo.controllers;

import com.example.demo.dto.requests.AddOrderRequest;
import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
	private final OrderService orderService;

	@GetMapping("/")
	public Iterable<Order> getAll() {
		return orderService.findAll();
	}

	@GetMapping("/{id}")
	public Order getById(@PathVariable Long id) {
		return orderService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Order add(@RequestBody @Valid @NotNull AddOrderRequest request) {
		throw new NotImplementedException();
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		orderService.deleteById(id);
	}

}
