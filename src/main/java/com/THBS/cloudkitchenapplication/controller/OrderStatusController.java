package com.THBS.cloudkitchenapplication.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.THBS.cloudkitchenapplication.entity.OrderStatus;
import com.THBS.cloudkitchenapplication.service.OrderStatusService;

@RestController
@RequestMapping("/orderstatus")
public class OrderStatusController {
    
    @Autowired
    private OrderStatusService orderStatusService;

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderStatus> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatus updatedOrderStatus) {
        // Check if the orderStatusId exists
        Optional<OrderStatus> existingOrderStatusOptional = orderStatusService.findByOrderId(orderId);
        if (!existingOrderStatusOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Get the existing orderStatus
        OrderStatus existingOrderStatus = existingOrderStatusOptional.get();

        // Update the order status fields
        existingOrderStatus.setStatus(updatedOrderStatus.getStatus());
        existingOrderStatus.setPrice(updatedOrderStatus.getPrice());

        // Save the updated orderStatus
        OrderStatus updatedStatus = orderStatusService.createOrderStatus(existingOrderStatus);

        return ResponseEntity.ok(updatedStatus);
    }
}

