package com.example.demo.repository;

import com.example.demo.entities.OrderAdditionalIngredient;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAdditionalIngredientRepository extends SpecificationJpaRepository<OrderAdditionalIngredient, Long> {
}
