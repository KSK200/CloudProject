package com.THBS.cloudkitchenapplication.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.entity.Dish;
import com.THBS.cloudkitchenapplication.entity.Order;
import com.THBS.cloudkitchenapplication.entity.OrderStatus;
import com.THBS.cloudkitchenapplication.entity.PaymentStatus;
import com.THBS.cloudkitchenapplication.repository.DishRepository;
import com.THBS.cloudkitchenapplication.repository.OrderRepository;
import com.THBS.cloudkitchenapplication.repository.OrderStatusRepository;
import com.THBS.cloudkitchenapplication.repository.PaymentStatusRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    public Order placeOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        // Save dishes associated with the order
        for (Dish dish : order.getDishes()) {
            dish.setOrder(savedOrder);
            dishRepository.save(dish);
        }

        // Create order status for the placed order (default: pending)
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrder(savedOrder);
        orderStatusRepository.save(orderStatus);

        // Create payment status for the placed order (default: not completed)
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setOrder(savedOrder);
        paymentStatusRepository.save(paymentStatus);

        return savedOrder;
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Other service methods as needed
}

