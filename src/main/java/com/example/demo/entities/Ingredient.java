package com.example.demo.entities;

import com.example.demo.converter.JsonStringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@Table(name="ingredients")
public class Ingredient {
    @Id
    @GeneratedValue
    private long id;
	@NonNull
    private String name;
    private double price;

	@Convert(converter = JsonStringListConverter.class)
	@Column(columnDefinition = "text")
    private List<String> allergen;

	@NonNull
    private String unit;

    @JsonIgnore
    @OneToMany(mappedBy = "ingredient")
    private List<DishIngredient> dishIngredientList;

    @OneToMany(mappedBy = "ingredient")
    private List<OrderAdditionalIngredient>  orderAdditionalIngredientList;

}
