package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "amount_of_ingredients")
public class DishIngredient {
	@EmbeddedId
	private DishIngredientId id = new DishIngredientId();
	static public final String id_ = "id";

	private double amount;
	static public final String amount_ = "amount";

	@ManyToOne
	@MapsId("dishId")
	@JoinColumn(name = "dish_id")
	private Dish dish;
	static public final String dish_ = "dish";

	@ManyToOne
	@MapsId("ingredientId")
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
	static public final String ingredient_ = "ingredient";
}

