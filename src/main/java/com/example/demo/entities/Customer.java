package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	static public final String id_ = "id";

	private String deliveryAddress;
	static public final String deliveryAddress_ = "deliveryAddress";

	private String phoneNumber;
	static public final String phoneNumber_ = "phoneNumber";

	@CreationTimestamp
	@Column(updatable = false) // Ensures it's never changed after creation
	private Date joinDate;
	static public final String joinDate_ = "joinDate";


	@JsonIgnore
	@OneToMany(mappedBy = Order.customer_)
	private List<Order> orders;
	static public final String orders_ = "orders";
}
