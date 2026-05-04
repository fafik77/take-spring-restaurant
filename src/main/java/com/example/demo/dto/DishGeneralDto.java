package com.example.demo.dto;

import com.example.demo.entities.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishGeneralDto {
	private Long id;
	private String name;
	private double price;
	private String description;

	public static DishGeneralDto fromEntity(Dish dish) {
		return new DishGeneralDto(dish.getId(), dish.getName(), dish.getPrice(), dish.getDescription());
	}
}
