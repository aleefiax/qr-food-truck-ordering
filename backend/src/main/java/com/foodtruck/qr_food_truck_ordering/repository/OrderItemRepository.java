package com.foodtruck.qr_food_truck_ordering.repository;

import com.foodtruck.qr_food_truck_ordering.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
