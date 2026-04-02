package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name="order items")

public class OrderDish {
    private int quantity;
    private String additionals;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn (name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn (name = "order_id")
    private Order order;
}
