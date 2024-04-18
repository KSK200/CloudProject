package com.THBS.cloudkitchenapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.THBS.cloudkitchenapplication.entity.Caterer;

@Repository
public interface CatererRepository extends JpaRepository<Caterer,Long>{

    Caterer findByUsername(String username);
    
}