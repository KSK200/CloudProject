package com.karthik.cloudkitchenapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthik.cloudkitchenapplication.entity.PaymentDetails;


@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {

    PaymentDetails findByCatererId(Long catererId);

}

