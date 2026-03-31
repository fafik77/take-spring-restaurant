package com.example.demo.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCustomerRequest {
	private String deliveryAddress;
	private String phoneNumber;
}
