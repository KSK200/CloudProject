package com.THBS.cloudkitchenapplication.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.entity.AccountDetails;
import com.THBS.cloudkitchenapplication.repository.AccountDetailsRepository;



@Service
public class AccountDetailsService {

    @Autowired
    private AccountDetailsRepository pDetailsRepository;

    @SuppressWarnings("null")
    public AccountDetails createPaymentDetails(AccountDetails user) {
        return pDetailsRepository.save(user);
    }

    public List<AccountDetails> getAllPaymentDetails() {
        return pDetailsRepository.findAll();
    }
    
    public AccountDetails findByCatererID(Long catererId) {
        return pDetailsRepository.findByCatererId(catererId);
    }

    
}