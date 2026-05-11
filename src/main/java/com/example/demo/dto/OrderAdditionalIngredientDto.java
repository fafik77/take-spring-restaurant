package com.example.demo.dto;

import com.example.demo.entities.OrderAdditionalIngredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

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
			//TODO: connect `IngredientController`.getById()
//			.add(linkTo(methodOn(IngredientController.class).getById(ingredient.getIngredient().getId()).withSelfRel());
			;
	}
}
