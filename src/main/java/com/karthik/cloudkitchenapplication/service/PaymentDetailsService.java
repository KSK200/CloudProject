package com.karthik.cloudkitchenapplication.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.cloudkitchenapplication.entity.PaymentDetails;
import com.karthik.cloudkitchenapplication.repository.PaymentDetailsRepository;



@Service
public class PaymentDetailsService {

    @Autowired
    private PaymentDetailsRepository pDetailsRepository;

    @SuppressWarnings("null")
    public PaymentDetails createPaymentDetails(PaymentDetails user) {
        return pDetailsRepository.save(user);
    }

    public List<PaymentDetails> getAllPaymentDetails() {
        return pDetailsRepository.findAll();
    }
    
    public PaymentDetails findByCatererID(Long catererId) {
        return pDetailsRepository.findByCatererId(catererId);
    }

    
}