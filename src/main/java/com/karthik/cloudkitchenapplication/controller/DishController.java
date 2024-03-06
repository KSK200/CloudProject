package com.karthik.cloudkitchenapplication.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karthik.cloudkitchenapplication.DTO.DishDTO;
import com.karthik.cloudkitchenapplication.entity.Dish;
import com.karthik.cloudkitchenapplication.service.DishService;

@RestController
@RequestMapping("/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping("/save")
    public ResponseEntity<Dish> saveDish(@RequestBody Dish dish) {
        Dish savedDish = dishService.saveDish(dish);
        
        return new ResponseEntity<>(savedDish, HttpStatus.CREATED);
    }

    
    // @GetMapping("/getalldishes")
    // public List<Dish> getAllDishes() {
    //     return dishService.getAllDishes();
    // }

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/getalldishes")
    public List<DishDTO> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        return convertToDTOs(dishes);
    }

    private List<DishDTO> convertToDTOs(List<Dish> dishes) {
        return dishes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DishDTO convertToDTO(Dish dish) {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setId(dish.getId());
        dishDTO.setName(dish.getName());
        // Check if the order associated with the dish is not null
        if (dish.getOrder() != null) {
            dishDTO.setOrderId(dish.getOrder().getId());
        } else {
            // Handle the scenario where the order is null
            dishDTO.setOrderId(null); // or any default value you prefer
        }
        return dishDTO;
    }
    // Other controller methods as needed
}
