package com.THBS.cloudkitchenapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailsDTO {
    private Long customerId;
    private String catererName;
    private String catererPhoneNumber;
    private String accountNumber;
    private String ifscCode;
    private String upiNumber;
    private double orderPrice;

}
