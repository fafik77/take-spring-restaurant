package com.example.demo.repository;

import com.example.demo.entities.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends SpecificationJpaRepository<Dish, Long> {

	@Query("SELECT d FROM Dish d JOIN d.ordersDishes od GROUP BY d.id ORDER BY COUNT(od) DESC")
	List<Dish> findMostPopularDishes();

	@Query("SELECT d FROM Dish d JOIN d.ordersDishes od JOIN od.additions oai GROUP BY d.id HAVING COUNT(oai) >= 1 ORDER BY COUNT(oai) DESC")
	List<Dish> findModifiedDishes();
}