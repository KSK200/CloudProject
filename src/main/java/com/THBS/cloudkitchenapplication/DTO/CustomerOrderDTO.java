package com.THBS.cloudkitchenapplication.DTO;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerOrderDTO {
    private Long orderId;
    private String customerName;
    private String catererName;
    private LocalDate deliveryDate;
    private int numberOfPeople;
    private double price;
    private String orderStatus;
    private List<DishesDTO> dishes;
}
