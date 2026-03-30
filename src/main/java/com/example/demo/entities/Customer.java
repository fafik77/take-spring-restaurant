package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue
	private long id;
	private String deliveryAddress;
	private String phoneNumber;
	private Date joinDate;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
}
