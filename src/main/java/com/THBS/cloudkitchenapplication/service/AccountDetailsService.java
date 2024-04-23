package com.THBS.cloudkitchenapplication.service;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.THBS.cloudkitchenapplication.entity.AccountDetails;
import com.THBS.cloudkitchenapplication.entity.Caterer;
import com.THBS.cloudkitchenapplication.repository.AccountDetailsRepository;
import com.THBS.cloudkitchenapplication.repository.CatererRepository;



@Service
public class AccountDetailsService {

    @Autowired
    private AccountDetailsRepository pDetailsRepository;

    @Autowired
    private CatererRepository catererRepository;

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

    public void saveAccountDetails(Long catererId, String accountNo, String ifscCode, String upiNumber, MultipartFile image) throws IOException {
        AccountDetails accountDetails = new AccountDetails();

        Caterer caterer = catererRepository.findById(catererId)
                .orElseThrow(() -> new RuntimeException("Caterer not found"));

        accountDetails.setCaterer(caterer);
        accountDetails.setAccountNo(accountNo);
        accountDetails.setIfscCode(ifscCode);
        accountDetails.setUpiNumber(upiNumber);

        if (image != null && !image.isEmpty()) {
            accountDetails.setImage(image.getBytes());
        }

        pDetailsRepository.save(accountDetails);
    }


    
}