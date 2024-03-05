package com.karthik.cloudkitchenapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.cloudkitchenapplication.entity.Dish;
import com.karthik.cloudkitchenapplication.repository.DishRepository;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @SuppressWarnings("null")
    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    // Other service methods as needed
}
