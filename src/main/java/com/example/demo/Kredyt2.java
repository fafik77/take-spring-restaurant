package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Kredyt2 {
	private Double kwota;
	private Double procent;
	private Integer ilerat;
	private Double rata;
	public double obliczRate() {
		double m = 1 - 1 / Math.pow(1.0 + procent / 12, ilerat);
		this.rata = kwota * (procent / 12) / m;
		return rata;
	}
}
