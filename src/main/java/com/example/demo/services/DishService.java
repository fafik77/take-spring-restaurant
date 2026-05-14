package com.example.demo.services;

import com.example.demo.dto.requests.AddDishRequest;
import com.example.demo.dto.requests.UpdateDishRequest;
import com.example.demo.dto.requests.ingredientAmount;
import com.example.demo.entities.Dish;
import com.example.demo.entities.DishIngredient;
import com.example.demo.entities.Ingredient;
import com.example.demo.exceptions.ItemInUseException;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.repository.DishIngredientRepository;
import com.example.demo.repository.DishRepository;
import com.example.demo.repository.IngredientRepository;
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
	private final IngredientRepository ingredientRepository;
	private final DishIngredientRepository dishIngredientRepository;

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

		Dish savedDish = dishRepository.save(dish);

		List<Long> ingredientIds = request.getIngredients().stream()
			.map(ingredientAmount::getId)
			.toList();

		List<Ingredient> fetchedIngredients = ingredientRepository.findAllById(ingredientIds);

		if (fetchedIngredients.size() != request.getIngredients().size()) {
			throw new ItemNotFoundException(Ingredient.class, String.join(", ", ingredientIds.stream().map(Object::toString).toList()));
		}

		List<DishIngredient> dishIngredients = request.getIngredients().stream().map(item -> {
			Ingredient ingredient = fetchedIngredients.stream()
				.filter(i -> i.getId() == item.getId())
				.findFirst()
				.orElseThrow();

			DishIngredient dishIngredient = new DishIngredient();
			dishIngredient.setDish(savedDish);
			dishIngredient.setIngredient(ingredient);

			dishIngredient.setAmount(item.getAmount());

			return dishIngredient;
		}).toList();

		dishIngredientRepository.saveAll(dishIngredients);

		return savedDish.getId();
	}

	@Transactional
	public void update(UpdateDishRequest request) {
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
		return dishRepository.findMostPopularDishes();
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