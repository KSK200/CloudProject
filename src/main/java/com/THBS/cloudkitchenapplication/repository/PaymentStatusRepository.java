package com.THBS.cloudkitchenapplication.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.THBS.cloudkitchenapplication.entity.PaymentStatus;

import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
    // Custom query methods if needed
      Optional<PaymentStatus> findByOrderId(Long orderId);
}
