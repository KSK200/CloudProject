package com.THBS.cloudkitchenapplication.service;

import com.THBS.cloudkitchenapplication.DTO.DishesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.DTO.OrderDetailsDTO;
import com.THBS.cloudkitchenapplication.entity.Customer;
import com.THBS.cloudkitchenapplication.repository.CustomerRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @SuppressWarnings("null")
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Customer createCustomer(Customer user) {
        user.setRole("Customer");
        return customerRepository.save(user);
    }

    // @SuppressWarnings("null")
    public Customer updateCustomer(Long id, Customer user) {
        if (customerRepository.existsById(id)) {
            user.setId(id);
            return customerRepository.save(user);
        }
        return null;
    }

    @SuppressWarnings("null")
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return customerRepository.findByUsername(username) != null;
    }
    public List<OrderDetailsDTO> findByOrderId(Long orderId) {
        List<Map<String, Object>> resultList = customerRepository.findByOrderId(orderId);
        List<OrderDetailsDTO> orderDetailsList = new ArrayList<>();
        List<DishesDTO> dishes = extractDishes(resultList); // Extract dishes first

        for (Map<String, Object> result : resultList) {
            Long orderIdFromQuery = (Long) result.get("orderId");
            Date deliveryDate = (Date) result.get("deliveryDate");
            int numberOfPeople = (int) result.get("numberOfPeople");
            Long catererId = (Long) result.get("catererId");
            Long customerId = (Long) result.get("customerId");

            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    orderIdFromQuery,
                    deliveryDate, // Use SQL Date directly
                    numberOfPeople,
                    catererId,
                    customerId,
                    extractDishes(resultList) // Use the extracted dishes list
            );
            orderDetailsList.add(orderDetailsDTO);
        }
        return orderDetailsList;
    }

    // Method to extract dishes from the result list
    public List<DishesDTO> extractDishes(List<Map<String, Object>> resultList) {
        List<DishesDTO> dishes = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
            Long dishId = (Long) result.get("dishId");
            String dishName = (String) result.get("dishName");
            DishesDTO dish = new DishesDTO(dishId, dishName);
            dishes.add(dish);
        }
        return dishes;
    }


    public List<OrderDetailsDTO> findAllOrderDetails() {
        List<Map<String, Object>> resultList = customerRepository.findAllOrderDetails();
        Map<Long, OrderDetailsDTO> orderDetailsMap = new HashMap<>();

        for (Map<String, Object> result : resultList) {
            Long orderId = (Long) result.get("orderId");
            Date deliveryDate = (Date) result.get("deliveryDate");
            int numberOfPeople = (int) result.get("numberOfPeople");
            Long catererId = (Long) result.get("catererId");
            Long customerId = (Long) result.get("customerId");

            OrderDetailsDTO orderDetailsDTO;
            if (!orderDetailsMap.containsKey(orderId)) {
                orderDetailsDTO = new OrderDetailsDTO(orderId, deliveryDate, numberOfPeople, catererId, customerId, new ArrayList<>());
                orderDetailsMap.put(orderId, orderDetailsDTO);
            } else {
                orderDetailsDTO = orderDetailsMap.get(orderId);
            }

            // Extract dish details
            Long dishId = (Long) result.get("dishId");
            String dishName = (String) result.get("dishName");
            DishesDTO dish = new DishesDTO(dishId, dishName);
            orderDetailsDTO.getDishes().add(dish);
        }

        return new ArrayList<>(orderDetailsMap.values());
    }



}
