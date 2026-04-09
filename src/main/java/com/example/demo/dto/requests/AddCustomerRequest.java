package com.example.demo.dto.requests;

import com.example.demo.entities.Customer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCustomerRequest {
	@NotNull
	@NotEmpty
	private String deliveryAddress;

	@NotNull
	@NotEmpty
	private String phoneNumber;

	public Customer mapToEntity() {
		return populateFields(new Customer());
	}

	public Customer populateFields(Customer customer) {
		customer.setDeliveryAddress(deliveryAddress);
		customer.setPhoneNumber(phoneNumber);
		return customer;
	}
}
