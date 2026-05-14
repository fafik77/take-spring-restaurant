package com.example.demo.dto;

import com.example.demo.controllers.DishController;
import com.example.demo.entities.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDetailsDto extends RepresentationModel<DishDetailsDto> {
	private Long id;
	private String name;
	private double price;
	private String description;
	private List<DishIngredientDto> dishIngredientList;

	public static DishDetailsDto fromEntity(Dish dish) {
		return new DishDetailsDto(dish.getId(), dish.getName(), dish.getPrice(), dish.getDescription(), dish.getDishIngredientList().stream().map(DishIngredientDto::fromEntity).toList())
			.add(linkTo(methodOn(DishController.class).getById(dish.getId())).withSelfRel());
	}
}
