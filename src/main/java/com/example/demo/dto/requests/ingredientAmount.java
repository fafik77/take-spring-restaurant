package com.example.demo.dto.requests;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ingredientAmount {
	private Long id;
	@Range(min = 0, message = "Amount cannot be negative")
	private Long amount;
}
