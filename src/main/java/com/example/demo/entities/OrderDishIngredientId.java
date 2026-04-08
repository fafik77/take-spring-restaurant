package com.example.demo.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDishIngredientId implements Serializable {
	private Long orderId;
	private Long dishId;
	private Long ingredientId;
}
