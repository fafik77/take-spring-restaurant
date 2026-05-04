package com.example.demo.controllers;

import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.dto.OrderGeneralDto;
import com.example.demo.dto.requests.AddOrderRequest;
import com.example.demo.entities.Order;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
	private final OrderService orderService;

	@GetMapping("/")
	public Iterable<OrderGeneralDto> getAll() {
		return StreamSupport.stream(
				orderService.findAll().spliterator(), false)
			.map(OrderGeneralDto::fromEntity).toList();
	}

	@GetMapping("/{id}")
	public OrderDetailsDto getById(@PathVariable Long id) {
		return OrderDetailsDto.fromEntity(
			orderService.findById(id).orElseThrow(() -> new ItemNotFoundException(Order.class, id)));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody @Valid @NotNull AddOrderRequest request) {
		throw new NotImplementedException();
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		orderService.deleteById(id);
	}

}
