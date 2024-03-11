package com.THBS.cloudkitchenapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.THBS.cloudkitchenapplication.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    
}
