package com.foodtruck.qr_food_truck_ordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foodtruck.qr_food_truck_ordering.model.MenuItem;
import com.foodtruck.qr_food_truck_ordering.model.OrderItem;
import com.foodtruck.qr_food_truck_ordering.repository.MenuItemRepository;
import com.foodtruck.qr_food_truck_ordering.repository.OrderItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository,
            MenuItemRepository menuItemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public OrderItem createOrderItem(OrderItem orderItem) {

        // validating to check if Item exists before reating OrderItem
        MenuItem menuItem = menuItemRepository.findByName(orderItem.getMenuItem().getName())
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        // checking if quantity is valid
        if (orderItem.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        // calculating price
        double calculatedPrice = menuItem.getPrice() * orderItem.getQuantity();
        orderItem.setPrice(calculatedPrice); // Set the calculated price

        orderItem.setMenuItem(menuItem);

        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {

        // validating to check if Item exists before reating OrderItem
        MenuItem menuItem = menuItemRepository.findByName(updatedOrderItem.getMenuItem().getName())
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        // checking if quantity is valid
        if (updatedOrderItem.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        return orderItemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setMenuItem(updatedOrderItem.getMenuItem());
                    existingItem.setQuantity(updatedOrderItem.getQuantity());

                    // Recalculate price
                    double newPrice = menuItem.getPrice() * updatedOrderItem.getQuantity();
                    existingItem.setPrice(newPrice);

                    existingItem.setPrice(newPrice);
                    return orderItemRepository.save(existingItem);
                })
                .orElseThrow(() -> new RuntimeException("Order item not found"));
    }

    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
        orderItemRepository.delete(orderItem);
    }

}
