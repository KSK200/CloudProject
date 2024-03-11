package com.THBS.cloudkitchenapplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.entity.OrderStatus;
import com.THBS.cloudkitchenapplication.repository.OrderStatusRepository;

@Service
public class OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @SuppressWarnings("null")
    public OrderStatus createOrderStatus(OrderStatus orderStatus) {
        // Additional logic if needed
        return orderStatusRepository.save(orderStatus);
    }

    public Optional<OrderStatus> findByOrderId(Long orderId) {
        return orderStatusRepository.findByOrderId(orderId);
    }
    public void updateStatusByOrderId(Long orderId, String status) {
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findByOrderId(orderId);
        if (optionalOrderStatus.isPresent()) {
            OrderStatus orderStatus = optionalOrderStatus.get();
            orderStatus.setStatus(status);
            orderStatusRepository.save(orderStatus);
        } else {
            // Handle case when OrderStatus for the given orderId does not exist
        }
    }

    public void updatePriceByOrderId(Long orderId, double price) {
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findByOrderId(orderId);
        if (optionalOrderStatus.isPresent()) {
            OrderStatus orderStatus = optionalOrderStatus.get();
            orderStatus.setPrice(price);
            orderStatusRepository.save(orderStatus);
        } else {
            // Handle case when OrderStatus for the given orderId does not exist
        }
    }
    // public List<OrderStatus> findByOrderId(Long orderId) {
    //     return OrderStatusRepository.findByOrderId(orderId);
    // }

    // public Optional<OrderStatus> getOrderById(Long orderStatusId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getOrderById'");
    // }

    // Other service methods as needed
}
