package com.THBS.cloudkitchenapplication.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // @Operation(summary = "This is used to get caterer account details by caterer id")
    // @GetMapping("/{id}")
    // public ResponseEntity<AccountDetailsDTO> getPaymentDetailsById(@PathVariable Long id) {
    //     AccountDetails paymentDetails = userService.findByCatererID(id);
    //     if (paymentDetails == null) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
        
    //     AccountDetailsDTO paymentDetailsDTO = new AccountDetailsDTO();
    //     paymentDetailsDTO.setId(paymentDetails.getId());
    //     paymentDetailsDTO.setAccountNo(paymentDetails.getAccountNo());
    //     paymentDetailsDTO.setIfscCode(paymentDetails.getIfscCode());
    //     paymentDetailsDTO.setUpiNumber(paymentDetails.getUpiNumber());
        
    //     return new ResponseEntity<>(paymentDetailsDTO, HttpStatus.OK);
    // }

    @Operation(summary = "This is used to Save Caterer account details")
    @PostMapping("/save")
    public ResponseEntity<?> saveAccountDetails(
            @RequestParam Long catererId,
            @RequestParam String accountNo,
            @RequestParam String ifscCode,
            @RequestParam String upiNumber,
            @RequestParam(required = false) MultipartFile image
    ) {
        try {
            userService.saveAccountDetails(catererId, accountNo, ifscCode, upiNumber, image);
            return ResponseEntity.ok().body("Account Details Saved Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to Save Account Details");
        }
    }

    
    @GetMapping("/{catererId}")
    public ResponseEntity<?> getAccountDetailsByCatererId(@PathVariable Long catererId) {
        AccountDetailsDTO accountDetailsDTO = userService.getAccountDetailsByCatererId(catererId);

        if (accountDetailsDTO == null) {
            return ((BodyBuilder) ResponseEntity.notFound()).body("No Account details found with CatererID  "+catererId);
        }

        return ResponseEntity.ok(accountDetailsDTO);
    }
}
