package com.THBS.cloudkitchenapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.THBS.cloudkitchenapplication.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
    List<Review> findByCatererId(Long catererId); 
}