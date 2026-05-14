package com.example.demo.repository;

import com.example.demo.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long>, JpaSpecificationExecutor<Dish> {

	@Query("SELECT d FROM Dish d JOIN d.ordersDishes od GROUP BY d.id ORDER BY COUNT(od) DESC")
	List<Dish> findMostPopularDishes();

	@Query("SELECT d FROM Dish d")
	List<Dish> findModifiedDishes();
}