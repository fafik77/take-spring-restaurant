package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	static public final String id_ = "id";

	@NonNull
	private String name;
	static public final String name_ = "name";

	private double price;
	static public final String price_ = "price";

	private String description;
	static public final String description_ = "description";


	@JsonIgnore
	@OneToMany(mappedBy = OrderDish.dish_)
	private List<OrderDish> ordersDishes;
	static public final String ordersDishes_ = "ordersDishes";

	@OneToMany(mappedBy = DishIngredient.dish_)
	private List<DishIngredient> dishIngredientList;
	static public final String dishIngredientList_ = "dishIngredientList";
}
