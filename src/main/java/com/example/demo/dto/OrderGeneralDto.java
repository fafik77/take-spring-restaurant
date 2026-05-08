package com.example.demo.dto;

import com.example.demo.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderGeneralDto {
	private Long id;
	private double totalPrice;
	private Date orderDate;
	private Date deliverDate;
	private Long customerId;
	//List of dishes is provided via Hateoas

	static public OrderGeneralDto fromEntity(Order order) {
		return new OrderGeneralDto(order.getId(), order.getTotalPrice(), order.getOrderDate(), order.getDeliverDate(), order.getCustomer().getId());
	}
}

