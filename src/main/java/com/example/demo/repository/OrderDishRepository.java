package com.example.demo.repository;

import com.example.demo.entities.OrderDish;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDishRepository extends SpecificationJpaRepository<OrderDish, Long> {
}
