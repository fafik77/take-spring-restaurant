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
@Table(name="ingredients")
public class Ingredient {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Decimal price;
    private String alergen;
    private String unit;

    @JsonIgnore
    @OneToMany(mappedBy = "ingredient")
    private List<DishIngredient> dishIngredientList;

    @OneToMany(mappedBy = "ingredient")
    private List<OrderAdditionalIngredient>  orderAdditionalIngredientList;

}
