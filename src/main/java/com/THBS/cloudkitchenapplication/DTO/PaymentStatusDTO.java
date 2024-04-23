package com.THBS.cloudkitchenapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusDTO {

    private Long id;
    private Long orderId;
    private String status = "not completed";

}
