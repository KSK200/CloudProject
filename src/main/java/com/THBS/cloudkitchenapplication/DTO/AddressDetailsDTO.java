package com.THBS.cloudkitchenapplication.DTO;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDetailsDTO {
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;
    private Long orderId;
    private LocalDate deliveryDate;
    private int numberOfPeople;
    private List<DishesDTO> dishes;
}
