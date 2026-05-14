package com.example.demo.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
public class AddDishRequest {
	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String description;

	@NotNull
	@Range(min = 0, message = "Price cannot be negative")
	private double price;

	@NotNull
	@NotEmpty
	private List<@NonNull Long> ingredientIds;
}
