package com.THBS.cloudkitchenapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private Long orderId;
    private Date deliveryDate;
    private int numberOfPeople;
    private Long catererId;
    private Long customerId;
    private List<DishesDTO> dishes;

    // Getters and Setters
}
