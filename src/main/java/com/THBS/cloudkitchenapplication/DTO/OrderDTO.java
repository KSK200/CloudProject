package com.THBS.cloudkitchenapplication.DTO;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTO {
    private Long id;
    private LocalDate deliveryDate;
    private int numberOfPeople;
    private Long customerId;
    private Long catererId;
    

    // Constructors, getters, and setters
}
