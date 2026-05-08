package com.example.demo.dto;

import com.example.demo.entities.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDetailsDto {
	private Long id;
	private String name;
	private double price;
	private String description;
	private List<DishIngredientDto> dishIngredientList;

	public static DishDetailsDto fromEntity(Dish dish) {
		return new DishDetailsDto(dish.getId(), dish.getName(), dish.getPrice(), dish.getDescription(), dish.getDishIngredientList().stream().map(DishIngredientDto::fromEntity).toList() );
	}
}
