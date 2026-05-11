package com.example.demo.dto.requests;

import com.example.demo.entities.Order;
import com.example.demo.entities.TakeoutOptions;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
public class AddOrderRequest {
	private TakeoutOptions takeoutOptions;
	@NotNull
	private Long customerId;

	@NotNull
	@NotEmpty
	private List<AddOrderDishItem> items;

	public Order mapToEntity() {
		return updateEntity(new Order());
	}

	public Order updateEntity(Order order) {
		order.setTakeoutOptions(takeoutOptions);
		return order;
	}
}
