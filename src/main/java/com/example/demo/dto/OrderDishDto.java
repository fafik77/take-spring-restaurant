package com.example.demo.dto;

import com.example.demo.controllers.DishController;
import com.example.demo.entities.OrderDish;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class OrderDishDto extends RepresentationModel<OrderDishDto> {
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
		)
			.add(linkTo(methodOn(DishController.class).getById(orderDish.getDish().getId())).withSelfRel());
	}

}
