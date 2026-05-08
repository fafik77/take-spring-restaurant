package com.example.demo.dto;

import com.example.demo.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	private long id;
	private Date joinDate;
	private String deliveryAddress;
	private String phoneNumber;

	static public CustomerDto fromEntity(Customer customer) {
		return new CustomerDto(
			customer.getId(),
			customer.getJoinDate(),
			customer.getDeliveryAddress(),
			customer.getPhoneNumber()
		);
	}
}
