package com.example.demo.repository;

import com.example.demo.entities.Dish;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends SpecificationJpaRepository<Dish, Long> {
}
