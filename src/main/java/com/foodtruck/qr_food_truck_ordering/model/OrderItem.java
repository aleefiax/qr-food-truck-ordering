package com.foodtruck.qr_food_truck_ordering.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Many OrderItems can belong to one MenuItem
    @JoinColumn(name = "menu_item_id") // Foreign key in MenuItem table
    private MenuItem menuItem;

    private Integer quantity;

    private Double price;

}
