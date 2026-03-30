package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "orders") //crucial to name it
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
//	@OneToMany
//	private List<OrderDish> dishes;
}
