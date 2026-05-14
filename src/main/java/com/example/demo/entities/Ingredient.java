package com.example.demo.entities;

import com.example.demo.converter.JsonStringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	static public final String id_ = "id";
	@NonNull
	private String name;
	private double price;

	@Convert(converter = JsonStringListConverter.class)
	@Column(columnDefinition = "text")
	private List<String> allergen;

	@Enumerated(EnumType.STRING)
	@NonNull
	private IngredientUnit unit;

	@JsonIgnore
	@OneToMany(mappedBy = DishIngredient.ingredient_)
	private List<DishIngredient> dishIngredientList;
	static public final String dishIngredientList_ = "dishIngredientList";

	@JsonIgnore
	@OneToMany(mappedBy = OrderAdditionalIngredient.ingredient_)
	private List<OrderAdditionalIngredient> orderAdditionalIngredientList;
	static public final String orderAdditionalIngredientList_ = "orderAdditionalIngredientList";

}
