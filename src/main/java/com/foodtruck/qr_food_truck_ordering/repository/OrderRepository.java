package com.foodtruck.qr_food_truck_ordering.repository;

import com.foodtruck.qr_food_truck_ordering.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom queries can be added here later if needed
}
