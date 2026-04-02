package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "dishes")
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private BigDecimal price;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "dish")
    private List <OrderDish> ordersDishes;
    @OneToMany(mappedBy = "dish_id")
    private List <DishIngredient> dishIngredientList;
}
