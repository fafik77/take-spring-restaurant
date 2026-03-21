package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/kredyt")
public class KredytController {
	@Autowired
	Kredyt1 kredyt1;

	@GetMapping
	public @ResponseBody Double getKredyt(
			@RequestParam Double kwota) {
		return kredyt1.obliczRate(kwota, 0.1, 12);
	}

}
