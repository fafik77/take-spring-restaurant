package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders") //crucial to name it
@Data
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	static public final String id_ = "id";

	private double totalPrice;
	static public final String totalPrice_ = "totalPrice";

	private boolean delivery;
	static public final String delivery_ = "delivery";

	private boolean takeout;
	static public final String takeout_ = "takeout";

	private boolean selfTakeout;
	static public final String selfTakeout_ = "selfTakeout";

	@CreationTimestamp
	@Column(updatable = false) // Ensures it's never changed after creation
	private Date orderDate = new Date();
	static public final String orderDate_ = "orderDate";

	private Date deliverDate;
	static public final String deliverDate_ = "deliverDate";


	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	static public final String customer_ = "customer";

	@OneToMany(mappedBy = OrderDish.order_)
	private List<OrderDish> dishes;
	static public final String dishes_ = "dishes";
}
