package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "amount of ingredients")
public class DishIngredient {
    private double amount;

    @ManyToOne
    @JoinColumn (name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn (name = "ingredient_id")
    private Ingredient ingredient;


}
