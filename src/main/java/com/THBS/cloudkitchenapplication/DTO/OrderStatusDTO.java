package com.THBS.cloudkitchenapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDTO {

    private Long id;
    private Long orderId;
    private String status = "pending";
    private double price = 0.0;

    
}
