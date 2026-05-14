package com.example.demo.dto;

import com.example.demo.entities.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DishGeneralDto extends RepresentationModel<DishGeneralDto> {

	private Long id;
	private String name;
	private double price;
	private String description;

	public static DishGeneralDto fromEntity(Dish dish) {
		return new DishGeneralDto(dish.getId(), dish.getName(), dish.getPrice(), dish.getDescription());
	}
}
