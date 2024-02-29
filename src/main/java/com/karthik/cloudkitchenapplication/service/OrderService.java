package com.karthik.cloudkitchenapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.cloudkitchenapplication.entity.Customer;
import com.karthik.cloudkitchenapplication.entity.Order;
import com.karthik.cloudkitchenapplication.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        Customer savedCustomer = CustomerService.saveCustomer(order.getCustomer());
        order.setCustomer(savedCustomer); // Set the saved customer to the order

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    // Other service methods as needed
}

