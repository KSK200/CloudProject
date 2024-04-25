package com.THBS.cloudkitchenapplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.THBS.cloudkitchenapplication.entity.PaymentStatus;

import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
  // Custom query methods if needed
  Optional<PaymentStatus> findByOrderId(Long orderId);

  @Query(value = "SELECT " +
      "c.id AS customer_id, " +
      "c.full_name AS customer_name, " +
      "c.phonenumber AS customer_phone, " +
      "c.email AS customer_email, " +
      "c.address AS customer_address, " +
      "o.id AS order_id, " +
      "o.delivery_date AS delivery_date, " +
      "o.number_of_people AS number_of_people, " +
      "d.id AS dish_id, " +
      "d.name AS dish_name " +
      "FROM orders o " +
      "INNER JOIN dish d ON o.id = d.order_id " +
      "INNER JOIN customer c ON o.customer_id = c.id " +
      "INNER JOIN payment_status ps ON o.id = ps.order_id " +
      "WHERE ps.status = 'completed' " +
      "AND c.id = :customerId", nativeQuery = true)
  List<Object[]> findOrderDetailsByCustomerIdAndPaymentStatus(Long customerId);

}
