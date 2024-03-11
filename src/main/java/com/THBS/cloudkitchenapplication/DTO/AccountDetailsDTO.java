package com.THBS.cloudkitchenapplication.DTO;

public class AccountDetailsDTO {

    private Long id;
    private String accountNo;
    private String ifscCode;
    private String upiNumber;

    public AccountDetailsDTO(){}

    public AccountDetailsDTO(Long id, String accountNo, String ifscCode, String upiNumber) {
        this.id = id;
        this.accountNo = accountNo;
        this.ifscCode = ifscCode;
        this.upiNumber = upiNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getUpiNumber() {
        return upiNumber;
    }

    public void setUpiNumber(String upiNumber) {
        this.upiNumber = upiNumber;
    }

    @Override
    public String toString() {
        return "PaymentDetailsDTO [id=" + id + ", accountNo=" + accountNo + ", ifscCode=" + ifscCode + ", upiNumber="
                + upiNumber + "]";
    }

    

    // Constructors, getters, and setters
}
