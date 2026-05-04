package com.example.demo.dto;

import com.example.demo.entities.OrderDish;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class
OrderDishDto {
	private long dishId;
	private String dishName;
	private int quantity;
	private double price;
	private List<OrderAdditionalIngredientDto> additions;

	static public OrderDishDto fromEntity(OrderDish orderDish) {
		return new OrderDishDto(
			orderDish.getDish().getId(),
			orderDish.getDish().getName(),
			orderDish.getQuantity(),
			orderDish.getPrice(),
			orderDish.getAdditions().stream().map(OrderAdditionalIngredientDto::fromEntity).toList()
		);
	}

}
