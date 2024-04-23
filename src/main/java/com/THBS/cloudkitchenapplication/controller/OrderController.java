package com.THBS.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.THBS.cloudkitchenapplication.DTO.OrderDTO;
import com.THBS.cloudkitchenapplication.DTO.PaymentDetailsDTO;
import com.THBS.cloudkitchenapplication.entity.Order;
import com.THBS.cloudkitchenapplication.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "This is used to store all the orders")
    @PostMapping("/")
    public ResponseEntity<String> saveOrder(@RequestBody Order order) {
        Order savedOrder = orderService.placeOrder(order);
        if (savedOrder == null) {
            return new ResponseEntity<>("Failed to place order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Order has been successfully placed", HttpStatus.CREATED);
    }
    
    @Operation(summary = "This is used to get all orders by customer ID")
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
    @Operation(summary = "This is used to get all Orders")
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
    @Operation(summary = "This is used to get all orders based order status ")
    @GetMapping("/Account/{customerId}/")
    public List<PaymentDetailsDTO> getOrdersByCustomerAndCaterer(
            @PathVariable("customerId") Long customerId){
        return orderService.getAccountDetailsByOrderStatus(customerId);
    }
    // Other controller methods as needed
}

