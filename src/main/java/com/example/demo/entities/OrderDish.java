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

	private int quantity;
	private double price;

	@ManyToOne
	@MapsId("dishId")
	@JoinColumn(name = "dish_id")
	private Dish dish;

	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;

	@OneToMany(mappedBy = "orderDish", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderAdditionalIngredient> additions;
}

