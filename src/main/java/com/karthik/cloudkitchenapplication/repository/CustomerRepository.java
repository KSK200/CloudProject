package com.karthik.cloudkitchenapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.cloudkitchenapplication.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    
}
