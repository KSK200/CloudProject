package com.THBS.cloudkitchenapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.THBS.cloudkitchenapplication.DTO.AccountDetailsDTO;
import com.THBS.cloudkitchenapplication.entity.AccountDetails;
import com.THBS.cloudkitchenapplication.service.AccountDetailsService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/Account")
public class AccountDetailsController {
    @Autowired
    private AccountDetailsService userService;

    @Operation(summary = "This is used to get all caterer account details")
    @GetMapping("/")
    public List<AccountDetails> getAllPayment() {
        return userService.getAllPaymentDetails();
    }
    @Operation(summary = "This is used to create caterer account details")
    @PostMapping("/")
    public ResponseEntity<String> createPaymentDetails(@RequestBody AccountDetails accountDetails) {
        // Assuming userService.createPaymentDetails returns the created account details
        AccountDetails createdAccountDetails = userService.createPaymentDetails(accountDetails);
        if (createdAccountDetails != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Account details uploaded successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload account details");
        }
    }

    @Operation(summary = "This is used to get caterer account details by caterer id")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDetailsDTO> getPaymentDetailsById(@PathVariable Long id) {
        AccountDetails paymentDetails = userService.findByCatererID(id);
        if (paymentDetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        AccountDetailsDTO paymentDetailsDTO = new AccountDetailsDTO();
        paymentDetailsDTO.setId(paymentDetails.getId());
        paymentDetailsDTO.setAccountNo(paymentDetails.getAccountNo());
        paymentDetailsDTO.setIfscCode(paymentDetails.getIfscCode());
        paymentDetailsDTO.setUpiNumber(paymentDetails.getUpiNumber());
        
        return new ResponseEntity<>(paymentDetailsDTO, HttpStatus.OK);
    }
}
