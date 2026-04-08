package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="order_items")

public class OrderDish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private int quantity;
    private String additionals;
    private double price;

    @ManyToOne
    @JoinColumn (name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn (name = "order_id")
    private Order order;
}
