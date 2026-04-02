package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
    @JoinColumn({
            @JoinColumn(name="order_id")
            @JoinColumn(name="dish_id")
    })
    private OrderDish orderDish;


}
