package com.example.demo.dto;

import com.example.demo.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDetailsDto {
	private Long id;
	private double totalPrice;
	private Date orderDate;
	private Date deliverDate;
	private Long customerId;
	private List<OrderDishDto> dishes;

	static public OrderDetailsDto fromEntity(Order order) {
		return new OrderDetailsDto(order.getId(), order.getTotalPrice(), order.getOrderDate(), order.getDeliverDate(), order.getCustomer().getId(), order.getDishes().stream().map(OrderDishDto::fromEntity).toList());
	}
}
