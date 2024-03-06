package com.karthik.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karthik.cloudkitchenapplication.DTO.OrderDTO;
import com.karthik.cloudkitchenapplication.entity.Order;
import com.karthik.cloudkitchenapplication.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        
        Order savedOrder = orderService.placeOrder(order);
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

    
    // @GetMapping("/getcustomerorderbyid/{customerId}")
    // public List<Order> getOrdersByCustomerId(@PathVariable Long customerId) {
    //     return orderService.getOrdersByCustomerId(customerId);
    // }

    @GetMapping("/getcustomerorderbyid/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> {
                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setId(order.getId());
                    orderDTO.setDeliveryDate(order.getDeliveryDate());
                    orderDTO.setNumberOfPeople(order.getNumberOfPeople());
                    orderDTO.setCustomerId(order.getCustomer().getId());
                    orderDTO.setCatererId(order.getCaterer().getId());
                    return orderDTO;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderDTOs);
    }
    @GetMapping("/getallorders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getALLOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> {
                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setId(order.getId());
                    orderDTO.setDeliveryDate(order.getDeliveryDate());
                    orderDTO.setNumberOfPeople(order.getNumberOfPeople());
                    orderDTO.setCustomerId(order.getCustomer().getId());
                    orderDTO.setCatererId(order.getCaterer().getId());
                    return orderDTO;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderDTOs);
    }

    // Other controller methods as needed
}

