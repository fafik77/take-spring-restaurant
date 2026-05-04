package com.example.demo.repository;

import com.example.demo.entities.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends SpecificationJpaRepository<Ingredient, Long> {
}
