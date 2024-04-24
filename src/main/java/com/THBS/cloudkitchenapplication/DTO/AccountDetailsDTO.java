package com.THBS.cloudkitchenapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDTO {

    private Long id;
    private String accountNo;
    private String ifscCode;
    private String upiNumber;
    // private byte[] qrCode;
    private String qrcode;    // Constructors, getters, and setters
}
