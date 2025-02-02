package com.foodtruck.qr_food_truck_ordering.service;

import com.foodtruck.qr_food_truck_ordering.model.FoodOrder;
import com.foodtruck.qr_food_truck_ordering.model.OrderItem;
import com.foodtruck.qr_food_truck_ordering.repository.FoodOrderRepository;
import com.foodtruck.qr_food_truck_ordering.repository.OrderItemRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // Marks this class as a service to be injected
public class FoodOrderService {

    private final FoodOrderRepository foodOrderRepository;
    private final OrderItemRepository orderItemRepository;

    public FoodOrderService(FoodOrderRepository foodOrderRepository, OrderItemRepository orderItemRepository) {
        this.foodOrderRepository = foodOrderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public FoodOrder createOrder(FoodOrder foodOrder) {
        List<OrderItem> validOrderItems = new ArrayList<>();

        for (OrderItem orderItem : foodOrder.getItems()) {
            OrderItem existingOrderItem = orderItemRepository.findById(orderItem.getId())
                    .orElseThrow(() -> new RuntimeException("OrderItem not found"));
            validOrderItems.add(existingOrderItem);
        }

        foodOrder.setItems(validOrderItems);
        return foodOrderRepository.save(foodOrder);
    }

    public List<FoodOrder> getAllOrders() {
        return foodOrderRepository.findAll();
    }

    public Optional<FoodOrder> getOrderById(Long id) {
        return foodOrderRepository.findById(id);
    }

    public FoodOrder updateOrderStatus(Long id, String newStatus) {
        FoodOrder order = foodOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        return foodOrderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        FoodOrder order = foodOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        foodOrderRepository.delete(order);
    }
}
