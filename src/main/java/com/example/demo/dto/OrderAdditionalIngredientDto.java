package com.example.demo.dto;

import com.example.demo.entities.OrderAdditionalIngredient;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class
OrderAdditionalIngredientDto {
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
		);

	}
}
