package com.karthik.cloudkitchenapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.cloudkitchenapplication.entity.Caterer;

public interface CatererRepository extends JpaRepository<Caterer,Long>{
    
}