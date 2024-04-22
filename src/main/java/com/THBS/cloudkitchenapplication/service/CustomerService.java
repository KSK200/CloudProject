package com.THBS.cloudkitchenapplication.service;

import com.THBS.cloudkitchenapplication.DTO.CustomerOrderDTO;
import com.THBS.cloudkitchenapplication.DTO.DishesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.DTO.OrderDetailsDTO;
import com.THBS.cloudkitchenapplication.entity.Customer;
import com.THBS.cloudkitchenapplication.repository.CustomerRepository;

import java.sql.Date;
import java.time.LocalDate;
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

    public List<CustomerOrderDTO> getOrdersByCustomerAndCaterer(Long customerId, Long catererId) {
        List<Object[]> results = customerRepository.getOrdersWithDishesByCustomerAndCaterer(customerId, catererId);
        Map<Long, CustomerOrderDTO> orderMap = new HashMap<>();
        
        for (Object[] row : results) {
            Long orderId = ((Number) row[0]).longValue();
            String customerName = (String) row[1];
            String catererName = (String) row[2];
            Date deliveryDateSQL = (Date) row[3]; // Retrieve as java.sql.Date
            LocalDate deliveryDate = deliveryDateSQL.toLocalDate(); // Convert to LocalDate
            int numberOfPeople = ((Number) row[4]).intValue();
            double price = ((Number) row[5]).doubleValue(); // Convert to double
            String orderStatus = (String) row[6];
            Long dishId = ((Number) row[7]).longValue();
            String dishName = (String) row[8];
            
            // If the order doesn't exist in the map, create a new one
            orderMap.putIfAbsent(orderId, new CustomerOrderDTO(orderId, customerName, catererName, deliveryDate, numberOfPeople, price, orderStatus, new ArrayList<>()));
            
            // Get the order from the map
            CustomerOrderDTO orderDTO = orderMap.get(orderId);
            
            // Create a new DishesDTO object and add it to the list of dishes for the order
            DishesDTO dishesDTO = new DishesDTO(dishId, dishName);
            orderDTO.getDishes().add(dishesDTO);
        }
        
        // Return the values (orders) from the map
        return new ArrayList<>(orderMap.values());
    }

}
