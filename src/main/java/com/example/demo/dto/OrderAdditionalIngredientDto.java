package com.example.demo.dto;

import com.example.demo.controllers.IngredientController;
import com.example.demo.entities.OrderAdditionalIngredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class OrderAdditionalIngredientDto extends RepresentationModel<OrderAdditionalIngredientDto> {
	private int amount;
	private String ingredientName;
	private Long ingredientId;
	private double overallPrice;

	public static OrderAdditionalIngredientDto fromEntity(OrderAdditionalIngredient ingredient) {
		var amount = ingredient.getAmountAdditional();
		return new OrderAdditionalIngredientDto(
			amount,
			ingredient.getIngredient().getName(),
			ingredient.getIngredient().getId(),
			ingredient.getIngredient().getPrice() * amount
		)
			.add(linkTo(methodOn(IngredientController.class).getById(ingredient.getIngredient().getId())).withSelfRel());
	}
}
