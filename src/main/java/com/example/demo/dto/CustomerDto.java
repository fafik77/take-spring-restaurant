package com.example.demo.dto;

import com.example.demo.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends RepresentationModel<CustomerDto> {
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
