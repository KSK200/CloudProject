package com.karthik.cloudkitchenapplication.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karthik.cloudkitchenapplication.entity.Dish;
import com.karthik.cloudkitchenapplication.entity.Order;
import com.karthik.cloudkitchenapplication.repository.DishRepository;
import com.karthik.cloudkitchenapplication.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    // @SuppressWarnings("null")
    // public Order saveOrder(Order order) {
    //     // Customer savedCustomer = CustomerService.saveCustomer(order.getCustomer());
    //     // order.setCustomer(savedCustomer); // Set the saved customer to the order

    //     return orderRepository.save(order);
    // }

    
    @Autowired
    private DishRepository dishRepository;

    public Order placeOrder(Order order) {
        
        @SuppressWarnings("null")
        Order savedOrder = orderRepository.save(order);
        for (Dish dish : order.getDishes()) {
            dish.setOrder(savedOrder);
            dishRepository.save(dish);
        }
        return savedOrder;
    }
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    
    public List<Order> getALLOrders() {
        return orderRepository.findAll();
    }
    


    

    
    // public List<Order> getOrdersByCustomerId(Long customerId) {
    //     return orderRepository.findOrdersByCustomerId(customerId);
    // }
    // Other service methods as needed
}

