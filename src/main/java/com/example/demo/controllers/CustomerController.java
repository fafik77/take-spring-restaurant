package com.example.demo.controllers;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.OrderGeneralDto;
import com.example.demo.dto.requests.AddCustomerRequest;
import com.example.demo.dto.requests.UpdateCustomerRequest;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.services.CustomerService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {
	private final CustomerService customerService;

	@GetMapping("/")
	public CollectionModel<CustomerDto> getAll() {
		List<CustomerDto> customers = StreamSupport.stream(
				customerService.findAll().spliterator(), false)
			.map(customer -> {
				CustomerDto dto = CustomerDto.fromEntity(customer);
				// Add a self-link
				dto
					.add(linkTo(methodOn(CustomerController.class).getById(dto.getId())).withSelfRel())
					.add(linkTo(methodOn(CustomerController.class).getCustomerOrders(dto.getId())).withRel(Customer.orders_));
				return dto;
			}).toList();
		// add a link to this endpoint
		return CollectionModel.of(customers,
			linkTo(methodOn(CustomerController.class).getAll()).withSelfRel());
	}

	@GetMapping("/{id}")
	public CustomerDto getById(@PathVariable Long id) {
		return CustomerDto.fromEntity(
				customerService.findById(id).orElseThrow(() -> new ItemNotFoundException(Customer.class, id))
			)
			.add(linkTo(methodOn(CustomerController.class).getById(id)).withSelfRel())
			.add(linkTo(methodOn(CustomerController.class).getCustomerOrders(id)).withRel(Customer.orders_));
	}

	@GetMapping("/{id}/orders")
	public Iterable<OrderGeneralDto> getCustomerOrders(@PathVariable Long id) {
		return StreamSupport.stream(
				customerService.getCustomerOrders(id).spliterator(), true)
			.map(OrderGeneralDto::fromEntity).toList();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDto add(@RequestBody @Valid @NonNull AddCustomerRequest request) {
		var dto = CustomerDto.fromEntity(customerService.add(request));
		dto.add(linkTo(methodOn(CustomerController.class).getById(dto.getId())).withSelfRel());
		return dto;
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
