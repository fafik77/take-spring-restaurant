package com.example.demo.dto.requests;

import com.example.demo.entities.Ingredient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
public class AddIngredientRequest {
	@NotBlank
	private String name;

	@PositiveOrZero
	private double price;

	private List<String> allergen;

	@NotBlank
	private String unit;

	public Ingredient mapToEntity() {
		return updateEntity(new Ingredient());
	}

	public Ingredient updateEntity(Ingredient ingredient) {
		ingredient.setName(name);
		ingredient.setPrice(price);
		ingredient.setAllergen(allergen);
		ingredient.setUnit(unit);
		return ingredient;
	}
}
