package com.karthik.cloudkitchenapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caterer_id")
    private Caterer caterer;

    private String accountNo;
    private String ifscCode;
    private String upiNumber;
    public AccountDetails(){}
    
    public AccountDetails(Long id, Caterer caterer, String accountNo, String ifscCode, String upiNumber) {
        this.id = id;
        this.caterer = caterer;
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

    public Caterer getCaterer() {
        return caterer;
    }

    public void setCaterer(Caterer caterer) {
        this.caterer = caterer;
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
        return "PaymentDetails [id=" + id + ", caterer=" + caterer + ", accountNo=" + accountNo + ", ifscCode="
                + ifscCode + ", upiNumber=" + upiNumber + "]";
    }
    

    
}
