package com.THBS.cloudkitchenapplication.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.DTO.CatererOrdersDTO;
import com.THBS.cloudkitchenapplication.DTO.DishesDTO;
import com.THBS.cloudkitchenapplication.entity.Caterer;
import com.THBS.cloudkitchenapplication.repository.CatererRepository;

@Service
public class CatererService {
    @Autowired
    private  CatererRepository catererRepository;

    public List<Caterer> getAllCaterers() {
        return catererRepository.findAll();
    }

    @SuppressWarnings("null")
    public Caterer getCatererById(Long id) {
        return catererRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Caterer createCaterer(Caterer user) {
        user.setRole("Caterer");
        return catererRepository.save(user);
    }

    @SuppressWarnings("null")
    public Caterer updateCaterer(Long id, Caterer user) {
        if (catererRepository.existsById(id)) {
            user.setId(id);
            return catererRepository.save(user);
        }
        return null;
    }

    @SuppressWarnings("null")
    public void deleteCaterer(Long id) {
        catererRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return catererRepository.findByUsername(username) != null;
    }

    public List<CatererOrdersDTO> getOrdersByCaterer(Long catererId) {
        List<Object[]> results = catererRepository.getOrdersWithDishesByCaterer(catererId);
        Map<Long, CatererOrdersDTO> orderMap = new HashMap<>();
        
        for (Object[] row : results) {
            Long orderId = ((Number) row[0]).longValue();
            String customerName = (String) row[1];
            String phoneNumber = (String) row[2];
            String email = (String) row[3];
            Date deliveryDateSql = (Date) row[4];
            LocalDate deliveryDate = deliveryDateSql.toLocalDate(); // Convert java.sql.Date to java.time.LocalDate
            int numberOfPeople = ((Number) row[5]).intValue();
            Long dishId = ((Number) row[6]).longValue(); // Extract dishId from the row
            String dishName = (String) row[7];
            
            // If the order doesn't exist in the map, create a new one
            orderMap.putIfAbsent(orderId, new CatererOrdersDTO(orderId, customerName, phoneNumber, email, deliveryDate, numberOfPeople,  new ArrayList<>()));
            
            // Get the order from the map
            CatererOrdersDTO orderDTO = orderMap.get(orderId);
            
            // Create a new DishesDTO object and add it to the list of dishes for the order
            DishesDTO dishesDTO = new DishesDTO(dishId, dishName);
            orderDTO.getDishes().add(dishesDTO);
        }
        
        // Return the values (orders) from the map
        return new ArrayList<>(orderMap.values());
    }

}
