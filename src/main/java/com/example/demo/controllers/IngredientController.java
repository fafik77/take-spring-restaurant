package com.example.demo.controllers;

import com.example.demo.dto.IdDto;
import com.example.demo.dto.requests.AddIngredientRequest;
import com.example.demo.entities.Ingredient;
import com.example.demo.exceptions.ItemNotFoundErrorDetails;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.services.IngredientService;
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
@RequestMapping(path = "/ingredients")
@RequiredArgsConstructor
@Validated
public class IngredientController {
	private final IngredientService ingredientService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get all ingredients")
	public CollectionModel<EntityModel<Ingredient>> getAll() {
		var ingredients = StreamSupport.stream(
				ingredientService.findAll().spliterator(), false)
			.map(ingredient -> EntityModel.of(ingredient,
				linkTo(methodOn(IngredientController.class).getById(ingredient.getId())).withSelfRel()))
			.toList();
		return CollectionModel.of(ingredients, linkTo(methodOn(IngredientController.class).getAll()).withSelfRel());
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get ingredient details by id")
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public EntityModel<Ingredient> getById(@PathVariable Long id) {
		return EntityModel.of(
			ingredientService.findById(id).orElseThrow(() -> new ItemNotFoundException(Ingredient.class, id)),
			linkTo(methodOn(IngredientController.class).getById(id)).withSelfRel()
		);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Add a new ingredient")
	public EntityModel<IdDto> add(@RequestBody @Valid @NotNull AddIngredientRequest request) {
		Long id = ingredientService.add(request);
		return EntityModel.of(new IdDto(id),
			linkTo(methodOn(IngredientController.class).getById(id)).withSelfRel());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Remove ingredient")
	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ItemNotFoundErrorDetails.class))})
	public void delete(@PathVariable Long id) {
		ingredientService.deleteById(id);
	}
}
