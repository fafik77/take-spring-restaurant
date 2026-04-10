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
	private String deliveryAddress;
	private String phoneNumber;
	@CreationTimestamp
	@Column(updatable = false) // Ensures it's never changed after creation
	private Date joinDate;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
}
