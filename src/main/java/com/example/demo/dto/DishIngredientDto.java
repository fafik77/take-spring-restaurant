package com.example.demo.dto;

import com.example.demo.controllers.IngredientController;
import com.example.demo.entities.DishIngredient;
import com.example.demo.entities.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishIngredientDto extends RepresentationModel<DishIngredientDto> {
	private double amount;
	private EntityModel<Ingredient> ingredient;

	public static DishIngredientDto fromEntity(DishIngredient dishIngredient) {
		return new DishIngredientDto(
			dishIngredient.getAmount(),
			EntityModel.of(dishIngredient.getIngredient())
				.add(linkTo(methodOn(IngredientController.class).getById(dishIngredient.getIngredient().getId())).withSelfRel())
		);
	}
}
