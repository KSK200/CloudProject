package com.karthik.cloudkitchenapplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.karthik.cloudkitchenapplication.entity.Customer;
import com.karthik.cloudkitchenapplication.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService userService;

    @GetMapping("/getallcustomers")
    public List<Customer> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @GetMapping("/getcustomerbyid/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return userService.getCustomerById(id);
    }

    @PostMapping("/savecustomer")
    public Customer createCustomer(@RequestBody Customer user) {
        return userService.createCustomer(user);
    }

    @PutMapping("/updatecustomer/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer user) {
        return userService.updateCustomer(id, user);
    }

    @DeleteMapping("/deletecustomer/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
    }
}
