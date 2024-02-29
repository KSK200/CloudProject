package com.karthik.cloudkitchenapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.cloudkitchenapplication.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
