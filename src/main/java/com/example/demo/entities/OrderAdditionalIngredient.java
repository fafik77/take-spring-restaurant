package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name="additional ingredients")
public class OrderAdditionalIngredient {
    private double amountAdditional;

    @ManyToOne
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="order_id"),
            @JoinColumn(name="dish_id")
    })
    private OrderDish orderDish;


}
