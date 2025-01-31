package com.foodtruck.qr_food_truck_ordering.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController // Marks this class as a REST API controller
@RequestMapping("/api") // Base URL for all endpoints in this class
public class HelloController {

    @GetMapping("/hello") // handels GET requests at /api/hello
    public String sayHello() {
        return "Hello to the foodtruck!";
    }
} 