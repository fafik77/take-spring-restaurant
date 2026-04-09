package com.example.demo.dto.requests;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddOrderDishItem(
	@NotNull
	int quantity,
	@NotNull
	long dish_id,

	List<AddAdditionalIngredientToItem> additions
) {
}
