package com.example.demo.dto.requests;

import com.example.demo.entities.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateCustomerRequest extends AddCustomerRequest {
	@NotNull
	private final Long id;

	public Customer populateFields(Customer customer) {
		return super.populateFields(customer);
	}
}
