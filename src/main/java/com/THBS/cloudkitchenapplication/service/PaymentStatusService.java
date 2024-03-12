package com.THBS.cloudkitchenapplication.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.THBS.cloudkitchenapplication.entity.PaymentStatus;
import com.THBS.cloudkitchenapplication.repository.PaymentStatusRepository;

@Service
public class PaymentStatusService {
    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    @SuppressWarnings("null")
    public PaymentStatus createPaymentStatus(PaymentStatus paymentStatus) {
        // Additional logic if needed
        return paymentStatusRepository.save(paymentStatus);
    }

    public Optional<PaymentStatus> findByOrderId(Long orderId) {
        return paymentStatusRepository.findByOrderId(orderId);
    }

    public void updateStatusByOrderId(Long orderId, String status) {
        Optional<PaymentStatus> paymentStatusOptional = paymentStatusRepository.findByOrderId(orderId);
        if (paymentStatusOptional.isPresent()) {
            PaymentStatus paymentStatus = paymentStatusOptional.get();
            paymentStatus.setStatus(status);
            paymentStatusRepository.save(paymentStatus);
        } else {
            // Handle the case when no PaymentStatus entity is found for the given orderId
            throw new IllegalArgumentException("No PaymentStatus found for orderId: " + orderId);
            // Alternatively, you can log a message or handle it based on your application's
            // requirements
        }
    }

    public Optional<PaymentStatus> getPaymentStatusByOrderId(Long orderId) {
        // TODO Auto-generated method stub
        return paymentStatusRepository.findByOrderId(orderId);
    }

    // Other service methods as needed
}