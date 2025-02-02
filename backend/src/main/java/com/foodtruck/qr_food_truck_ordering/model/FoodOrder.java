package com.foodtruck.qr_food_truck_ordering.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity // Marks class as JPA Entity
@Getter
@Setter
public class FoodOrder {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // how primary key is generated
    private Long id;

    private String customerName;

    @OneToMany(cascade = CascadeType.ALL) // One FoodOrder can have many OrderItems
    @JoinColumn(name = "food_order_id") // Foreign key in OrderItem table
    private List<OrderItem> items = new ArrayList<>();

    private String status; // pending, preparing, ready, completed

    @Column(nullable = false) // additional proeprties like col names and also constraints
    private LocalDateTime orderTime = LocalDateTime.now();
}