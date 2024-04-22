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
public class CatererOrdersDTO {
    private Long orderId;
    private String customerName;
    private String customerPhoneNumber;
    private String customerEmail;
    private LocalDate deliveryDate;
    private int numberOfPeople;
    private List<DishesDTO> dishes;
}
