package com.karthik.cloudkitchenapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karthik.cloudkitchenapplication.DTO.PaymentDetailsDTO;
import com.karthik.cloudkitchenapplication.entity.AccountDetails;
import com.karthik.cloudkitchenapplication.service.AccountDetailsService;


@RestController
@RequestMapping("/Account")
public class AccountDetailsController {
    @Autowired
    private AccountDetailsService userService;

    @GetMapping("/")
    public List<AccountDetails> getAllPayment() {
        return userService.getAllPaymentDetails();
    }
    
    @PostMapping("/")
    public AccountDetails createPaymentDetails(@RequestBody AccountDetails user) {
        return userService.createPaymentDetails(user);
    }

     @GetMapping("/{id}")
    public ResponseEntity<PaymentDetailsDTO> getPaymentDetailsById(@PathVariable Long id) {
        AccountDetails paymentDetails = userService.findByCatererID(id);
        if (paymentDetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO();
        paymentDetailsDTO.setId(paymentDetails.getId());
        paymentDetailsDTO.setAccountNo(paymentDetails.getAccountNo());
        paymentDetailsDTO.setIfscCode(paymentDetails.getIfscCode());
        paymentDetailsDTO.setUpiNumber(paymentDetails.getUpiNumber());
        
        return new ResponseEntity<>(paymentDetailsDTO, HttpStatus.OK);
    }
}
