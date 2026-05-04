package com.example.demo.controllers;

import com.example.demo.dto.DishDetailsDto;
import com.example.demo.dto.DishGeneralDto;
import com.example.demo.entities.Dish;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/dishes")
@RequiredArgsConstructor
@Validated
public class DishController {
	private final DishService dishService;

	@GetMapping("/")
	public Iterable<DishGeneralDto> getAll() {
		return dishService.findAll().stream().map(DishGeneralDto::fromEntity).toList();
	}

	@GetMapping("/{id}")
	public DishDetailsDto getById(Long id) {
		return DishDetailsDto.fromEntity(dishService.findById(id).orElseThrow(() -> new ItemNotFoundException(Dish.class, id)));
	}


//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Customer add(@RequestBody @Valid @NonNull ) {
//		return dishService.add(request);
//	}
//
//	@PutMapping
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void update(@RequestBody @Valid @NonNull ) {
//		dishService.update(request);
//	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		dishService.deleteById(id);
	}


}
