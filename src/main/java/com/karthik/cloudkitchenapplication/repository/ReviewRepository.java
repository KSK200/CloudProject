package com.karthik.cloudkitchenapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.cloudkitchenapplication.entity.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
    
}