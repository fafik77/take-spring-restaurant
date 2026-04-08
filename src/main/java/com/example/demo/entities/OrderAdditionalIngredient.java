package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name="additional_ingredients")
public class OrderAdditionalIngredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private double amountAdditional;

    @ManyToOne
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;
    @ManyToOne
	@JoinColumn(name="order_dish_id")
    private OrderDish orderDish;


}
