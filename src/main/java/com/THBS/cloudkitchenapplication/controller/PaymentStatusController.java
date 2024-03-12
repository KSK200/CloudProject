package com.THBS.cloudkitchenapplication.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.THBS.cloudkitchenapplication.DTO.PaymentStatusDTO;
import com.THBS.cloudkitchenapplication.entity.PaymentStatus;
import com.THBS.cloudkitchenapplication.service.PaymentStatusService;

@RestController
@RequestMapping("/paymentstatus")
public class PaymentStatusController {

    @Autowired
    private PaymentStatusService paymentStatusService;

    @PutMapping("/{orderId}")
    public ResponseEntity<PaymentStatus> updatePaymentStatus(@PathVariable Long orderId, @RequestBody PaymentStatus updatedPaymentStatus) {
        // Check if the paymentStatusId exists
        java.util.Optional<PaymentStatus> existingPaymentStatusOptional = paymentStatusService.findByOrderId(orderId);
        if (!existingPaymentStatusOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Get the existing paymentStatus
        PaymentStatus existingPaymentStatus = existingPaymentStatusOptional.get();

        // Update the payment status fields
        existingPaymentStatus.setStatus(updatedPaymentStatus.getStatus());
        // existingPaymentStatus.setPrice(updatedPaymentStatus.getPrice());

        // Save the updated paymentStatus
        PaymentStatus updatedStatus = paymentStatusService.createPaymentStatus(existingPaymentStatus);

        return ResponseEntity.ok(updatedStatus);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentStatusDTO> getPaymentStatusByOrderId(@PathVariable Long orderId) {
    Optional<PaymentStatus> paymentStatusOptional = paymentStatusService.getPaymentStatusByOrderId(orderId);
    
    if (paymentStatusOptional.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    PaymentStatus paymentStatus = paymentStatusOptional.get();
    PaymentStatusDTO paymentStatusDTO = new PaymentStatusDTO();
    paymentStatusDTO.setId(paymentStatus.getId());
    paymentStatusDTO.setOrderId(paymentStatus.getOrder().getId());
    paymentStatusDTO.setStatus(paymentStatus.getStatus());

    return ResponseEntity.ok(paymentStatusDTO);
}



}

