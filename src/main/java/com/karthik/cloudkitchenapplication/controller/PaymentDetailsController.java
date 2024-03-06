package com.karthik.cloudkitchenapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karthik.cloudkitchenapplication.DTO.PaymentDetailsDTO;
import com.karthik.cloudkitchenapplication.entity.PaymentDetails;
import com.karthik.cloudkitchenapplication.service.PaymentDetailsService;


@RestController
@RequestMapping("/payment")
public class PaymentDetailsController {
    @Autowired
    private PaymentDetailsService userService;

    @GetMapping("/getallpayments")
    public List<PaymentDetails> getAllPayment() {
        return userService.getAllPaymentDetails();
    }
    
    @PostMapping("/save")
    public PaymentDetails createPaymentDetails(@RequestBody PaymentDetails user) {
        return userService.createPaymentDetails(user);
    }
    
    // @GetMapping("/getbyid/{catererId}")
    // public List<PaymentDetails> getOrdersByCustomerId(@PathVariable Long catererId) {
    //     return userService.findByCatererID(catererId);
    // }

     @GetMapping("/getbyid/{id}")
    public ResponseEntity<PaymentDetailsDTO> getPaymentDetailsById(@PathVariable Long id) {
        PaymentDetails paymentDetails = userService.findByCatererID(id);
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
