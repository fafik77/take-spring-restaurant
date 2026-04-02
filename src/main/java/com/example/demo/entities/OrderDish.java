package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="order items")

public class OrderDish {
    private int quantity;
    private String additionals;
    private Decimal price;

    @ManyToOne
    @JoinColumn (name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn (name = "order_id")
    private Order order;
}
