package com.example.demo.dto;

import com.example.demo.controllers.OrderController;
import com.example.demo.entities.Order;
import com.example.demo.entities.TakeoutOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class OrderDetailsDto extends RepresentationModel<OrderDetailsDto> {
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.00")
	private double totalPrice;
	private TakeoutOptions takeoutOption;
	private Date orderDate;
	private Date deliverDate;
	private Long customerId;
	private List<OrderDishDto> dishes;

	static public OrderDetailsDto fromEntity(Order order) {
		return new OrderDetailsDto(order.getId(), order.getTotalPrice(), order.getTakeoutOptions(), order.getOrderDate(), order.getDeliverDate(), order.getCustomer().getId(),
			order.getDishes().stream().map(OrderDishDto::fromEntity).toList())
			.add(linkTo(methodOn(OrderController.class).getById(order.getId())).withSelfRel());
	}
}
