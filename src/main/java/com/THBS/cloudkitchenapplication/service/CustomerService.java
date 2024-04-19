package com.THBS.cloudkitchenapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.DTO.OrderDetailsDTO;
import com.THBS.cloudkitchenapplication.entity.Customer;
import com.THBS.cloudkitchenapplication.repository.CustomerRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<OrderDetailsDTO> getOrderDetails(Long orderId) {
        List<Object[]> resultList = customerRepository.findByOrderId(orderId);
        List<OrderDetailsDTO> orderDetailsList = new ArrayList<>();
        for (Object[] result : resultList) {
            Long orderIdFromQuery = (Long) result[0];
            Date deliveryDate = (Date) result[1];
            int numberOfPeople = (int) result[2];
            Long catererId = (Long) result[3];
            Long customerId = (Long) result[4];
            Long dishId = (Long) result[5];
            String dishName = (String) result[6];
            
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    orderIdFromQuery,
                    deliveryDate,
                    numberOfPeople,
                    catererId,
                    customerId,
                    dishId,
                    dishName
            );
            orderDetailsList.add(orderDetailsDTO);
        }
        return orderDetailsList;
    }
    
}
