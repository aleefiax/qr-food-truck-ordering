package com.foodtruck.qr_food_truck_ordering.repository;

import com.foodtruck.qr_food_truck_ordering.model.MenuItem;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByName(String name); // Find by name instead of ID
}
