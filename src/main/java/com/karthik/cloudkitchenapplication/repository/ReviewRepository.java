package com.karthik.cloudkitchenapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.cloudkitchenapplication.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{
    
}