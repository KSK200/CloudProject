package com.THBS.cloudkitchenapplication.DTO;


import java.sql.Date;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class OrderDetailsDTO {
    private Long orderId;
    private Date deliveryDate;
    private int numberOfPeople;
    private Long catererId;
    private Long customerId;
    private Long dishId;
    private String dishName;

    // Adjusted constructor to use java.sql.Date
    public OrderDetailsDTO(Long orderId, Date deliveryDate2, int numberOfPeople, Long catererId, Long customerId, Long dishId, String dishName) {
        this.orderId = orderId;
        this.deliveryDate = deliveryDate2;
        this.numberOfPeople = numberOfPeople;
        this.catererId = catererId;
        this.customerId = customerId;
        this.dishId = dishId;
        this.dishName = dishName;
    }

}
