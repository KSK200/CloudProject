package com.karthik.cloudkitchenapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.cloudkitchenapplication.entity.Caterer;

@Repository
public interface CatererRepository extends JpaRepository<Caterer,Long>{
    
}