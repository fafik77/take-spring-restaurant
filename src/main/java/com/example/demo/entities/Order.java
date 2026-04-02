package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders") //crucial to name it
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double totalPrice;
	private boolean delivery;
	private boolean takeout;
	private boolean selfTakeout;
	@CreationTimestamp
	@Column(updatable = false) // Ensures it's never changed after creation
	private Date orderDate;
	private Date deliverDate;


	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
    @OneToMany
    private List<OrderDish> dishes;
}
