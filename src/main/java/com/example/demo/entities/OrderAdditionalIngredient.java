package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "additional_ingredients")
public class OrderAdditionalIngredient {
	@EmbeddedId
	private OrderDishIngredientId id = new OrderDishIngredientId();
	private int amountAdditional;

	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@MapsId("dishId")
	@JoinColumn(name = "dish_id")
	private Dish dish;

	@ManyToOne
	@MapsId("ingredientId")
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable = false, updatable = false),
		@JoinColumn(name = "dish_id", referencedColumnName = "dish_id", insertable = false, updatable = false)
	})
	private OrderDish orderDish;
}

