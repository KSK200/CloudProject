package com.karthik.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karthik.cloudkitchenapplication.entity.Order;
import com.karthik.cloudkitchenapplication.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        
        Order savedOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // @PostMapping("/save")
    // public ResponseEntity<Order> createOrder(@RequestBody Order orderDTO) {
    //     Order order = orderService.saveOrder(orderDTO);
    //     return new ResponseEntity<>(order, HttpStatus.CREATED);
    // }

    // @GetMapping("/customer")
    // public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
    //     List<Order> orders = orderService.getOrdersByCustomerId(customerId);
    //     return new ResponseEntity<>(orders, HttpStatus.OK);
    // }

    
    @GetMapping("/getcustomerorderbyid/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

     
    // Other controller methods as needed
}

