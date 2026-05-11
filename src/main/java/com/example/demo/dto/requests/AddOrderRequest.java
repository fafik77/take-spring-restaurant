package com.example.demo.dto.requests;

import com.example.demo.entities.Order;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
public class AddOrderRequest {
	private boolean delivery;
	private boolean takeout;
	private boolean selfTakeout;
	@NotNull
	private Long customerId;

	@NotNull
	@NotEmpty
	private List<AddOrderDishItem> items;

	public Order mapToEntity() {
		return updateEntity(new Order());
	}

	public Order updateEntity(Order order) {
		order.setDelivery(delivery);
		order.setTakeout(takeout);
		order.setSelfTakeout(selfTakeout);
		return order;
	}
}
