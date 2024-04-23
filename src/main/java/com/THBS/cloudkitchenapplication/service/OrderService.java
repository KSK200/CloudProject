package com.THBS.cloudkitchenapplication.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.DTO.PaymentDetailsDTO;
import com.THBS.cloudkitchenapplication.entity.Dish;
import com.THBS.cloudkitchenapplication.entity.Order;
import com.THBS.cloudkitchenapplication.entity.OrderStatus;
import com.THBS.cloudkitchenapplication.entity.PaymentStatus;
import com.THBS.cloudkitchenapplication.repository.DishRepository;
import com.THBS.cloudkitchenapplication.repository.OrderRepository;
import com.THBS.cloudkitchenapplication.repository.OrderStatusRepository;
import com.THBS.cloudkitchenapplication.repository.PaymentStatusRepository;

import java.util.ArrayList;
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

    public List<PaymentDetailsDTO> getAccountDetailsByOrderStatus(Long customerId) {
        List<Object[]> results = orderRepository.findDistinctByCustomerAndStatusAccepted(customerId);
        List<PaymentDetailsDTO> paymentDetailsDTO = new ArrayList<>();
        
        for (Object[] row : results) {
            Long orderId = ((Number) row[0]).longValue();
            String catererName = (String) row[1];
            String catererPhoneNumber = (String) row[2];
            String accountNumber = (String) row[3];
            String ifscCode = (String) row[4];
            String upiNumber = (String) row[5];
            double price = (double) row[6];
            
            PaymentDetailsDTO orderDetails = new PaymentDetailsDTO(orderId, catererName, catererPhoneNumber, accountNumber, ifscCode, upiNumber, price);
            paymentDetailsDTO.add(orderDetails);
        }
        
        return paymentDetailsDTO;
    }
}