package com.example.demo.services;

import com.example.demo.dto.requests.AddDishRequest;
import com.example.demo.dto.requests.UpdateDishRequest;
import com.example.demo.entities.Dish;
import com.example.demo.exceptions.ItemInUseException;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public Long add(AddDishRequest request) {
		Dish dish = new Dish();
		dish.setName(request.getName());
		dish.setDescription(request.getDescription());
		dish.setPrice(request.getPrice());

		return dishRepository.save(dish).getId();
	}

	@Transactional
	public void update(UpdateDishRequest request) {
		if (request.getId() == null) {
			throw new IllegalArgumentException("ID is required for update");
		}

		Dish existingDish = dishRepository.findById(request.getId())
			.orElseThrow(() -> new ItemNotFoundException(Dish.class, request.getId()));

		if (isDishInUse(existingDish.getId())) {
			throw new ItemInUseException(Dish.class, existingDish.getId());
		}

		existingDish.setName(request.getName());
		existingDish.setDescription(request.getDescription());
		existingDish.setPrice(request.getPrice());

		dishRepository.save(existingDish);
	}

	@Transactional
	public void deleteById(Long id) {
		if (!dishRepository.existsById(id)) {
			throw new ItemNotFoundException(Dish.class, id);
		}

		if (isDishInUse(id)) throw new ItemInUseException(Dish.class, id);

		dishRepository.deleteById(id);
	}

	public List<Dish> findMostPopularDishes() {
		return dishRepository .findMostPopularDishes();
	}

	public List<Dish> findModifiedDishes() {
		return dishRepository.findModifiedDishes();
	}


	private boolean isDishInUse(Long id) {
		Specification<Dish> dishHasOrders = (root, _, cb) -> cb.and(
			cb.equal(root.get(Dish.id_), id),
			cb.isNotEmpty(root.get(Dish.ordersDishes_))
		);
		return dishRepository.exists(dishHasOrders);
	}
}