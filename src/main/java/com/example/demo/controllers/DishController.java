package com.example.demo.controllers;

import com.example.demo.dto.DishDetailsDto;
import com.example.demo.dto.DishGeneralDto;
import com.example.demo.dto.IdDto;
import com.example.demo.dto.requests.AddDishRequest;
import com.example.demo.dto.requests.UpdateDishRequest;
import com.example.demo.entities.Dish;
import com.example.demo.exceptions.ItemNotFoundErrorDetails;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.services.DishService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/dishes")
@RequiredArgsConstructor
@Validated
public class DishController {

	private final DishService dishService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get all dishes")
	public CollectionModel<DishGeneralDto> getAll() {
		var dishes = dishService.findAll().stream()
			.map(ent -> DishGeneralDto.fromEntity(ent)
				.add(linkTo(methodOn(DishController.class).getById(ent.getId())).withSelfRel())
			).toList();
		return CollectionModel.of(dishes, linkTo(methodOn(DishController.class).getAll()).withSelfRel());
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get dish details by id")
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public EntityModel<DishDetailsDto> getById(@PathVariable Long id) {
		return EntityModel.of(
			DishDetailsDto.fromEntity(
				dishService.findById(id).orElseThrow(() -> new ItemNotFoundException(Dish.class, id)))
		);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Add a new dish")
	public EntityModel<IdDto> add(@RequestBody @Valid @NotNull AddDishRequest request) {
		Long id = dishService.add(request);
		return EntityModel.of(new IdDto(id),
			linkTo(methodOn(DishController.class).getById(id)).withSelfRel());
	}

	@PutMapping("/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Update an existing dish (price cannot be changed if in use)")
	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public void update(@RequestBody @Valid @NotNull UpdateDishRequest request) {
		dishService.update(request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Remove dish (RESTRICT if order exists, cascade otherwise)")
	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public void delete(@PathVariable Long id) {
		dishService.deleteById(id);
	}

	@GetMapping("/reports/most-popular")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get most frequently ordered dishes")
	public CollectionModel<DishGeneralDto> getMostPopular() {
		var dishes = dishService.findMostPopularDishes().stream()
			.map(ent -> DishGeneralDto.fromEntity(ent)
				.add(linkTo(methodOn(DishController.class).getById(ent.getId())).withSelfRel())
			).toList();
		return CollectionModel.of(dishes, linkTo(methodOn(DishController.class).getMostPopular()).withSelfRel());
	}

	@GetMapping("/reports/modified")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get modified dishes")
	public CollectionModel<DishGeneralDto> getModifiedDishes() {
		var dishes = dishService.findModifiedDishes().stream()
			.map(ent -> DishGeneralDto.fromEntity(ent)
				.add(linkTo(methodOn(DishController.class).getById(ent.getId())).withSelfRel())
			).toList();
		return CollectionModel.of(dishes, linkTo(methodOn(DishController.class).getModifiedDishes()).withSelfRel());
	}
}