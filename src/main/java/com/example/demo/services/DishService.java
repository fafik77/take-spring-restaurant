package com.example.demo.services;

import com.example.demo.entities.Dish;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.repository.DishRepository;
import lombok.RequiredArgsConstructor;
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

	public void deleteById(Long id) {
		if (!dishRepository.existsById(id))
			throw new ItemNotFoundException(Dish.class, id);
		dishRepository.deleteById(id);
	}
}
