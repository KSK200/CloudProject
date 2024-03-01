package com.karthik.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karthik.cloudkitchenapplication.entity.Dish;
import com.karthik.cloudkitchenapplication.service.DishService;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping("/save")
    public ResponseEntity<Dish> saveDish(@RequestBody Dish dish) {
        Dish savedDish = dishService.saveDish(dish);
        
        return new ResponseEntity<>(savedDish, HttpStatus.CREATED);
    }

    // Other controller methods as needed
}
