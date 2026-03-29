package com.example.demo.controllers;

import com.example.demo.Kredyt1;
import com.example.demo.Kredyt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController //why RestController? It automatically applies @ResponseBody = JSON
@RequestMapping(path="/kredyt")
public class KredytController {
	@Autowired
	Kredyt1 kredyt1;

	@GetMapping
	public @ResponseBody Double getKredyt(
			@RequestParam Double kwota) {
		return kredyt1.obliczRate(kwota, 0.1, 12);
	}
	@GetMapping("{kwota}")
	public @ResponseBody Double getKredyt2(
			@PathVariable Double kwota) {
		return kredyt1.obliczRate(kwota, 0.1, 12);
	}

	@PostMapping
	public @ResponseBody Double postKredyt(
			@RequestBody Kredyt2 kredyt){
		return kredyt.obliczRate();
	}

}

