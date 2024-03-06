package com.karthik.cloudkitchenapplication.service;
import java.util.List;
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

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    // Other service methods as needed
}
