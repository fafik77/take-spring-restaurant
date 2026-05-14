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
public class RegularCustomerDto extends RepresentationModel<RegularCustomerDto> {
	private long id;
	private Date joinDate;
	private String deliveryAddress;
	private String phoneNumber;
	private Long orders;

	static public RegularCustomerDto fromEntity(Customer customer, Long orders) {
		return new RegularCustomerDto(
			customer.getId(),
			customer.getJoinDate(),
			customer.getDeliveryAddress(),
			customer.getPhoneNumber(),
			orders
		);
	}
}
