package com.example.demo.dto;

import com.example.demo.entities.OrderDish;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.00")
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
