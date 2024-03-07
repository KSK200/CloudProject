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
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        
        Order savedOrder = orderService.placeOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
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
    @GetMapping("/")
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

