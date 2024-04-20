package com.THBS.cloudkitchenapplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.THBS.cloudkitchenapplication.DTO.OrderDetailsDTO;
import com.THBS.cloudkitchenapplication.entity.Customer;
import com.THBS.cloudkitchenapplication.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService userService;

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return userService.getCustomerById(id);
    }

    // @PostMapping("/")
    // public Customer createCustomer(@RequestBody Customer user) {
    //     return userService.createCustomer(user);
    // }
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Customer user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userService.createCustomer(user);
        return new ResponseEntity<>("User signed up successfully!", HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer user) {
        return userService.updateCustomer(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(@PathVariable Long orderId) {
        List<OrderDetailsDTO> orderDetails = userService.findByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetails.get(0)); // Assuming the list contains only one order
    }

    @GetMapping("/allorders")
    public ResponseEntity<List<OrderDetailsDTO>> findAllOrderDetail() {
        List<OrderDetailsDTO> orderDetails = userService.findAllOrderDetails();
        if (orderDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetails);
    }



}
