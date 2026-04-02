package com.example.demo.dto.requests;

import com.example.demo.entities.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddOrderRequest {
	private boolean delivery;
	private boolean takeout;
	private boolean selfTakeout;

	private Long customerId;
}
