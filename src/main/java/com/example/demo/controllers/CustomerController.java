package com.example.demo.controllers;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.IdDto;
import com.example.demo.dto.OrderGeneralDto;
import com.example.demo.dto.RegularCustomerDto;
import com.example.demo.dto.requests.AddCustomerRequest;
import com.example.demo.dto.requests.UpdateCustomerRequest;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.ItemNotFoundErrorDetails;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get all customers")
	public CollectionModel<CustomerDto> getAll() {
		List<CustomerDto> customers = StreamSupport.stream(
				customerService.findAll().spliterator(), false)
			.map(customer -> CustomerDto.fromEntity(customer)
				.add(linkTo(methodOn(CustomerController.class).getById(customer.getId())).withSelfRel())
				.add(linkTo(methodOn(CustomerController.class).getCustomerOrders(customer.getId())).withRel(Customer.orders_))).toList();
		// add a link to this endpoint
		return CollectionModel.of(customers,
			linkTo(methodOn(CustomerController.class).getAll()).withSelfRel());
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get customer details by id")
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public EntityModel<CustomerDto> getById(@PathVariable Long id) {
		var dto = CustomerDto.fromEntity(
				customerService.findById(id).orElseThrow(() -> new ItemNotFoundException(Customer.class, id))
			)
			.add(linkTo(methodOn(CustomerController.class).getById(id)).withSelfRel())
			.add(linkTo(methodOn(CustomerController.class).getCustomerOrders(id)).withRel(Customer.orders_));
		return EntityModel.of(dto)
			.add(linkTo(methodOn(CustomerController.class).getById(id)).withSelfRel());
	}

	@GetMapping("/{id}/orders")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get customer orders")
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public CollectionModel<OrderGeneralDto> getCustomerOrders(@PathVariable Long id) {
		var dto = StreamSupport.stream(
				customerService.getCustomerOrders(id).spliterator(), true)
			.map(o -> OrderGeneralDto.fromEntity(o)
				.add(linkTo(methodOn(OrderController.class).getById(o.getId())).withSelfRel())
				.add(linkTo(methodOn(CustomerController.class).getById(o.getCustomer().getId())).withRel("customer"))
			).toList();
		return CollectionModel.of(dto,
			linkTo(methodOn(CustomerController.class).getCustomerOrders(id)).withSelfRel());
	}

	@GetMapping("/regulars")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get regular customers")
	public CollectionModel<RegularCustomerDto> getRegulars() {
		List<RegularCustomerDto> regulars = customerService.getRegulars();
		return CollectionModel.of(
			regulars.stream().map(r -> r.add(linkTo(methodOn(CustomerController.class).getById(r.getId())).withSelfRel())).toList(),
			linkTo(methodOn(CustomerController.class).getRegulars()).withSelfRel());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Add a customer")
	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public EntityModel<IdDto> add(@RequestBody @Valid @NonNull AddCustomerRequest request) {
		var id = customerService.add(request).getId();
		return EntityModel.of(new IdDto(id),
			linkTo(methodOn(CustomerController.class).getById(id)).withSelfRel());
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Update customer information")
	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public void update(@RequestBody @Valid @NonNull UpdateCustomerRequest request) {
		customerService.update(request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Remove customer by id")
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public void delete(@PathVariable Long id) {
		customerService.deleteById(id);
	}


}
