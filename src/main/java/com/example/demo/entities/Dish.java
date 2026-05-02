package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@NonNull
    private String name;
    private double price;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "dish")
    private List <OrderDish> ordersDishes;
    @OneToMany(mappedBy = "dish")
    private List <DishIngredient> dishIngredientList;
}
