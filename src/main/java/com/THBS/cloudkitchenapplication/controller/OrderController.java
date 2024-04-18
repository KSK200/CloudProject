package com.THBS.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.THBS.cloudkitchenapplication.DTO.OrderDTO;
import com.THBS.cloudkitchenapplication.entity.Order;
import com.THBS.cloudkitchenapplication.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<String> saveOrder(@RequestBody Order order) {
        Order savedOrder = orderService.placeOrder(order);
        if (savedOrder == null) {
            return new ResponseEntity<>("Failed to place order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Order has been successfully placed", HttpStatus.CREATED);
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
        List<Order> orders = orderService.getAllOrders();
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

