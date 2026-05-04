package com.example.demo.dto;

import com.example.demo.entities.DishIngredient;
import com.example.demo.entities.Ingredient;

public record DishIngredientDto(double amount, Ingredient ingredient) {
	public static DishIngredientDto fromEntity(DishIngredient dishIngredient) {
		return new DishIngredientDto(dishIngredient.getAmount(), dishIngredient.getIngredient());
	}
}
