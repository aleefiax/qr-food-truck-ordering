package com.foodtruck.qr_food_truck_ordering.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity // Marks class as JPA Entity
@Getter
@Setter
public class FoodOrder {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // how primary key is generated
    private Long id;

    private String customerName;
    private String itmes;
    private String status; // pending, preparing, ready, completed

    @Column(nullable = false) // additional proeprties like col names and also constraints
    private LocalDateTime orderTime = LocalDateTime.now();
}
