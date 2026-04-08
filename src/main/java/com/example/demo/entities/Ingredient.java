package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="ingredients")
public class Ingredient {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double price;
    private String allergen;
    private String unit;

    @JsonIgnore
    @OneToMany(mappedBy = "ingredient")
    private List<DishIngredient> dishIngredientList;

    @OneToMany(mappedBy = "ingredient")
    private List<OrderAdditionalIngredient>  orderAdditionalIngredientList;

}
