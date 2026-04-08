package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "amount_of_ingredients")
public class DishIngredient {
	@EmbeddedId
	private DishIngredientId id = new DishIngredientId();
	private double amount;

	@ManyToOne
	@MapsId("dishId")
	@JoinColumn(name = "dish_id")
	private Dish dish;

	@ManyToOne
	@MapsId("ingredientId")
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
}

