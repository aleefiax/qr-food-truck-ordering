package com.foodtruck.qr_food_truck_ordering.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodtruck.qr_food_truck_ordering.model.FoodOrder;
import com.foodtruck.qr_food_truck_ordering.repository.OrderRepository;
import com.foodtruck.qr_food_truck_ordering.service.OrderService;

@RestController // Marks this class as a REST API controller
@RequestMapping("/orders") // Base URL for all endpoints in this class
public class OrderController {

    private final OrderService orderService;

    // Constructor
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    // requestBody annotations tells spring to expect a JSON body that will be
    // mapped to the order object
    // responseEntity is used to retuen a response, including status code and the
    // data
    @PostMapping
    public ResponseEntity<FoodOrder> createOrder(@RequestBody FoodOrder order) {
        FoodOrder savedOrder = orderService.createOrder(order); // save
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder); // return saved order
    }

    // Get all orders
    @GetMapping
    public List<FoodOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get order by ID
    // @PathVariable binds the id in the URL to the method parameter
    @GetMapping("/{Id}")
    public ResponseEntity<FoodOrder> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(order -> ResponseEntity.ok(order)) // Found the order
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Order not found
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodOrder> updateOrderStatus(@PathVariable Long id, @RequestBody String newStatus) {
        try {
            FoodOrder updatedOrder = orderService.updateOrderStatus(id, newStatus);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
