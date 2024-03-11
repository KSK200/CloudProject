package com.THBS.cloudkitchenapplication.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.entity.Dish;
import com.THBS.cloudkitchenapplication.repository.DishRepository;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @SuppressWarnings("null")
    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public List<Dish> findByOrderId(Long orderId) {
        return dishRepository.findByOrderId(orderId);
    }


    // Other service methods as needed
}
