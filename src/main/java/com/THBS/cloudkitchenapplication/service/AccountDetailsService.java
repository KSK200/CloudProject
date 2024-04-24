package com.THBS.cloudkitchenapplication.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.THBS.cloudkitchenapplication.DTO.AccountDetailsDTO;
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
            accountDetails.setQrcode(image.getBytes());
        }

        pDetailsRepository.save(accountDetails);
    }

    public AccountDetailsDTO getAccountDetailsByCatererId(Long catererId) {
        AccountDetails accountDetails = pDetailsRepository.findByCatererId(catererId);
    
        if (accountDetails == null) {
            return null; // Return null if no account details are found
        }
    
        return mapToDTO(accountDetails);
    }
    
    private AccountDetailsDTO mapToDTO(AccountDetails accountDetails) {
        AccountDetailsDTO dto = new AccountDetailsDTO();
        dto.setId(accountDetails.getId());
        dto.setAccountNo(accountDetails.getAccountNo());
        dto.setIfscCode(accountDetails.getIfscCode());
        dto.setUpiNumber(accountDetails.getUpiNumber());
        
        // Encode QR code byte array as Base64 and set it to qrcode field
        if (accountDetails.getQrcode() != null) {
            String qrCodeBase64 = Base64.getEncoder().encodeToString(accountDetails.getQrcode());
            dto.setQrcode(qrCodeBase64);
        }
    
        return dto;
    }
    
}