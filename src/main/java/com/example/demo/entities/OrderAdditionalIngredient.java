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
	static public final String id_ = "id";

	private int amountAdditional;
	static public final String amountAdditional_ = "amountAdditional";

	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;
	static public final String order_ = "order";

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

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable = false, updatable = false),
		@JoinColumn(name = "dish_id", referencedColumnName = "dish_id", insertable = false, updatable = false)
	})
	private OrderDish orderDish;
	static public final String orderDish_ = "orderDish";
}

