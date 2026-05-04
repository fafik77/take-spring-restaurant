package com.example.demo.services;

import com.example.demo.entities.Dish;
import com.example.demo.exceptions.ItemInUseException;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {
	private final DishRepository dishRepository;

	public List<Dish> findAll() {
		return dishRepository.findAll();
	}

	public Optional<Dish> findById(Long id) {
		return dishRepository.findById(id);
	}

	/**
	 * Removes Dish from the menu only if it was not ordered.
	 * the `DishIngredient` table has entries removed as well.
	 *
	 * @param id Dish to remove
	 */
	public void deleteById(Long id) {
		if (!dishRepository.existsById(id))
			throw new ItemNotFoundException(Dish.class, id);
		Specification<Dish> dishHasOrders = (root, query, cb) -> {
			return cb.and(
				cb.equal(root.get(Dish.id_), id), // filter by Dish ID
				cb.isNotEmpty(root.get(Dish.ordersDishes_)) // Check for orders in that dish
			);
		};
		//check if predicate is true
		if (dishRepository.exists(dishHasOrders))
			throw new ItemInUseException(Dish.class, id);
		dishRepository.deleteById(id);
	}
}
