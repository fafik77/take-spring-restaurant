package com.example.demo.dto;

import com.example.demo.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class OrderGeneralDto extends RepresentationModel<OrderGeneralDto> {
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

