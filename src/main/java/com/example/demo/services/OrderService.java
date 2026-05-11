package com.example.demo.services;

import com.example.demo.dto.requests.AddOrderRequest;
import com.example.demo.entities.*;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DishRepository;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.OrderRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final DishRepository dishRepository;
	private final IngredientRepository ingredientRepository;

	public Iterable<Order> findAll() {
		return orderRepository.findAll();
	}

	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}

	@Transactional
	public Long add(@Valid @NotNull AddOrderRequest request) {
		var customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new ItemNotFoundException(Customer.class, request.getCustomerId()));
		var order = request.mapToEntity();
		order.setCustomer(customer);

		List<OrderDish> dishes = request.getItems().stream().map(item ->
			{
				var dish = dishRepository.findById(item.dish_id()).orElseThrow(() -> new ItemNotFoundException(Dish.class, item.dish_id()));
				var orderedDish = new OrderDish();
				orderedDish.setDish(dish);
				orderedDish.setOrder(order);
				orderedDish.setQuantity(item.quantity());
				var price = dish.getPrice() * item.quantity();
				if (item.additions() != null && !item.additions().isEmpty()) {
					List<OrderAdditionalIngredient> additions = item.additions().stream().map(add ->
						{
							var ingr = ingredientRepository.findById(add.ingredient_id()).orElseThrow(() -> new ItemNotFoundException(Ingredient.class, add.ingredient_id()));
							var addIngr = new OrderAdditionalIngredient();
							addIngr.setIngredient(ingr);
							addIngr.setOrderDish(orderedDish);
							addIngr.setDish(dish);
							addIngr.setOrder(order);
							addIngr.setAmountAdditional(add.amount());
							return addIngr;
						}
					).toList();
					orderedDish.setAdditions(additions);
					price += additions.stream()
						.map(it -> it.getAmountAdditional() * it.getIngredient().getPrice()).reduce(0.0, Double::sum)
						* item.quantity();
				}
				price = Math.round(price * 100.0) / 100.0; //ensure 2 decimal places
				orderedDish.setPrice(price);
				return orderedDish;
			}
		).toList();

		order.setDishes(dishes);
		var savedOrder = orderRepository.save(order);
		return savedOrder.getId();
	}
}
