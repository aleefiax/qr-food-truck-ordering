package com.foodtruck.qr_food_truck_ordering.repository;

import com.foodtruck.qr_food_truck_ordering.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    // Custom queries can be added here later if needed
}