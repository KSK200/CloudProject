package com.THBS.cloudkitchenapplication.service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.DTO.AddressDetailsDTO;
import com.THBS.cloudkitchenapplication.DTO.DishesDTO;
import com.THBS.cloudkitchenapplication.entity.PaymentStatus;
import com.THBS.cloudkitchenapplication.repository.PaymentStatusRepository;

@Service
public class PaymentStatusService {
    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    @SuppressWarnings("null")
    public PaymentStatus createPaymentStatus(PaymentStatus paymentStatus) {
        // Additional logic if needed
        return paymentStatusRepository.save(paymentStatus);
    }

    public Optional<PaymentStatus> findByOrderId(Long orderId) {
        return paymentStatusRepository.findByOrderId(orderId);
    }

    public void updateStatusByOrderId(Long orderId, String status) {
        Optional<PaymentStatus> paymentStatusOptional = paymentStatusRepository.findByOrderId(orderId);
        if (paymentStatusOptional.isPresent()) {
            PaymentStatus paymentStatus = paymentStatusOptional.get();
            paymentStatus.setStatus(status);
            paymentStatusRepository.save(paymentStatus);
        } else {
            // Handle the case when no PaymentStatus entity is found for the given orderId
            throw new IllegalArgumentException("No PaymentStatus found for orderId: " + orderId);
            // Alternatively, you can log a message or handle it based on your application's
            // requirements
        }
    }

    public Optional<PaymentStatus> getPaymentStatusByOrderId(Long orderId) {
        // TODO Auto-generated method stub
        return paymentStatusRepository.findByOrderId(orderId);
    }

    
    public List<AddressDetailsDTO> getOrdersByCaterer(Long customerId) {
        List<Object[]> results = paymentStatusRepository.findOrderDetailsByCustomerIdAndPaymentStatus(customerId);
        Map<Long, AddressDetailsDTO> orderMap = new HashMap<>();

        for (Object[] row : results) {
            Long customer_Id = ((Number) row[0]).longValue();
            String customerName = (String) row[1];
            String customerPhone = (String) row[2];
            String customerEmail = (String) row[3];
            String customerAddress = (String) row[4];
            Long orderId = ((Number) row[5]).longValue();
            Date deliveryDateSql = (Date) row[6];
            LocalDate deliveryDate = deliveryDateSql.toLocalDate();
            int numberOfPeople = ((Number) row[7]).intValue();
            Long dishId = ((Number) row[8]).longValue();
            String dishName = (String) row[9];

            // If the order doesn't exist in the map, create a new one
            if (!orderMap.containsKey(orderId)) {
                AddressDetailsDTO orderDTO = new AddressDetailsDTO(customer_Id, customerName, customerPhone,
                        customerEmail, customerAddress, orderId, deliveryDate, numberOfPeople, new ArrayList<>());
                orderMap.put(orderId, orderDTO);
            }

            // Get the order from the map
            AddressDetailsDTO orderDTO = orderMap.get(orderId);

            // Create a new DishesDTO object and add it to the list of dishes for the order
            DishesDTO dishesDTO = new DishesDTO(dishId, dishName);
            orderDTO.getDishes().add(dishesDTO);
        }

        // Return the values (orders) from the map
        return new ArrayList<>(orderMap.values());
    }
    // Other service methods as needed
}