package com.foodtruck.qr_food_truck_ordering.service;

import com.foodtruck.qr_food_truck_ordering.model.FoodOrder;
import com.foodtruck.qr_food_truck_ordering.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as a service to be injected
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public FoodOrder createOrder(FoodOrder order) {
        return orderRepository.save(order);
    }

    public List<FoodOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<FoodOrder> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public FoodOrder updateOrderStatus(Long id, String newStatus) {
        FoodOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        FoodOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}
