package com.foodtruck.qr_food_truck_ordering.service;

import com.foodtruck.qr_food_truck_ordering.model.MenuItem;
import com.foodtruck.qr_food_truck_ordering.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public Optional<MenuItem> getMenuItemById(Long id) {
        return menuItemRepository.findById(id);
    }

    public MenuItem updateMenuItem(Long id, MenuItem updatedMenuItem) { 
        return menuItemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setName(updatedMenuItem.getName());
                    existingItem.setPrice(updatedMenuItem.getPrice());
                    return menuItemRepository.save(existingItem);
                })
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }

    public void deleteMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        menuItemRepository.delete(menuItem);
    }
}
