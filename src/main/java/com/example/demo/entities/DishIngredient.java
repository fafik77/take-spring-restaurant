package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "amount_of_ingredients")
public class DishIngredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private double amount;

    @ManyToOne
    @JoinColumn (name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn (name = "ingredient_id")
    private Ingredient ingredient;


}
