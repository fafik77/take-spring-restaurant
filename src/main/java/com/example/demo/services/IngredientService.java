package com.example.demo.services;

import com.example.demo.dto.requests.AddIngredientRequest;
import com.example.demo.entities.Ingredient;
import com.example.demo.exceptions.IngredientPriceChangeInUseException;
import com.example.demo.exceptions.ItemInUseException;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.repository.IngredientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {
	private final IngredientRepository ingredientRepository;

	public Iterable<Ingredient> findAll() {
		return ingredientRepository.findAll();
	}

	public Optional<Ingredient> findById(Long id) {
		return ingredientRepository.findById(id);
	}

	public Long add(@Valid @NotNull AddIngredientRequest request) {
		var ingredient = ingredientRepository.save(request.mapToEntity());
		return ingredient.getId();
	}

	public void update(Long id, @Valid @NotNull AddIngredientRequest request) {
		var ingredient = ingredientRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(Ingredient.class, id));

		if (Double.compare(ingredient.getPrice(), request.getPrice()) != 0 && ingredientRepository.exists(ingredientIsUsed(id)))
			throw new IngredientPriceChangeInUseException(id);

		ingredientRepository.save(request.updateEntity(ingredient));
	}

	public void deleteById(Long id) {
		if (!ingredientRepository.existsById(id))
			throw new ItemNotFoundException(Ingredient.class, id);

		if (ingredientRepository.exists(ingredientIsUsed(id)))
			throw new ItemInUseException(Ingredient.class, id);

		ingredientRepository.deleteById(id);
	}

	private Specification<Ingredient> ingredientIsUsed(Long id) {
		return (root, query, cb) -> cb.and(
			cb.equal(root.get(Ingredient.id_), id),
			cb.or(
				cb.isNotEmpty(root.get(Ingredient.dishIngredientList_)),
				cb.isNotEmpty(root.get(Ingredient.orderAdditionalIngredientList_))
			)
		);
	}
}
