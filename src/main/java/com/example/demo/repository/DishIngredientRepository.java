package com.example.demo.repository;

import com.example.demo.entities.DishIngredient;
import org.springframework.stereotype.Repository;

@Repository
public interface DishIngredientRepository extends SpecificationJpaRepository<DishIngredient, Long> {
}

