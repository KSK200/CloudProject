package com.THBS.cloudkitchenapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.THBS.cloudkitchenapplication.entity.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    // Custom query methods if needed
    Optional<OrderStatus> findByOrderId(Long orderId);
}
