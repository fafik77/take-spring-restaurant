package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderDish {
	@EmbeddedId
	private DishOrderId id = new DishOrderId();
	static public final String id_ = "id";

	private int quantity;
	static public final String quantity_ = "quantity";

	private double price;
	static public final String price_ = "price";

	@ManyToOne
	@MapsId("dishId")
	@JoinColumn(name = "dish_id")
	private Dish dish;
	static public final String dish_ = "dish";

	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;
	static public final String order_ = "order";

	@OneToMany(mappedBy = "orderDish", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderAdditionalIngredient> additions;
	static public final String additions_ = "additions";
}

