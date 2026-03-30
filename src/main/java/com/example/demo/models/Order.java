package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue
	private long id;
	private double totalPrice;
	private boolean delivery;
	private boolean takeout;
	private boolean selfTakeout;
	private Date orderDate;
	private Date deliverDate;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
//	@ManyToMany
//	@JoinTable(
//			name = "order_dish",
//			joinColumns = @JoinColumn(name = "order_id"),
//			inverseJoinColumns = @JoinColumn(name = "dish_id")
//	)
//	private List<Dish> dishes;
}
