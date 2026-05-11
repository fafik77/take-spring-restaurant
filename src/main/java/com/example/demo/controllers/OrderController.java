package com.example.demo.controllers;

import com.example.demo.dto.IdDto;
import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.dto.OrderGeneralDto;
import com.example.demo.dto.requests.AddOrderRequest;
import com.example.demo.entities.Order;
import com.example.demo.exceptions.ItemNotFoundErrorDetails;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
	private final OrderService orderService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get all orders")
	public CollectionModel<OrderGeneralDto> getAll() {
		var orders = StreamSupport.stream(
				orderService.findAll().spliterator(), false)
			.map(ent -> OrderGeneralDto.fromEntity(ent)
				.add(linkTo(methodOn(OrderController.class).getById(ent.getId())).withSelfRel())
			).toList();
		return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).getAll()).withSelfRel());
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get order details by id")
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public EntityModel<OrderDetailsDto> getById(@PathVariable Long id) {
		return EntityModel.of(
			OrderDetailsDto.fromEntity(
				orderService.findById(id).orElseThrow(() -> new ItemNotFoundException(Order.class, id)))
		);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Add a new order")
	public EntityModel<IdDto> add(@RequestBody @Valid @NotNull AddOrderRequest request) {
		Long id = orderService.add(request);
		return EntityModel.of(new IdDto(id),
			linkTo(methodOn(OrderController.class).getById(id)).withSelfRel());
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Remove order (not older than 30sec)")
	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public void delete(@PathVariable Long id) {
		orderService.deleteById(id);
	}

}
