package com.THBS.cloudkitchenapplication.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.THBS.cloudkitchenapplication.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByOrderId(Long orderId);
}

