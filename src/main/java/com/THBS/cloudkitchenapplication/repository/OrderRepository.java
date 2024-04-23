package com.THBS.cloudkitchenapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.THBS.cloudkitchenapplication.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);

    @Query(value = "SELECT distinct " +
            "    c.id AS customer_id, " +
            "    ca.full_name AS caterer_name, " +
            "    ca.phone_number AS caterer_phonenumber, " +
            "    ad.account_no AS account_number, " +
            "    ad.ifsc_code AS ifsc_code, " +
            "    ad.upi_number AS upi_number, " +
            "    os.price AS order_price " +
            "FROM " +
            "    Orders o " +
            "INNER JOIN " +
            "    order_status os ON o.id = os.order_id " +
            "INNER JOIN " +
            "    customer c ON o.customer_id = c.id " +
            "INNER JOIN " +
            "    caterer ca ON o.caterer_id = ca.id " +
            "INNER JOIN " +
            "    account_details ad ON ca.id = ad.caterer_id " +
            "WHERE " +
            "    os.status = 'Accepted' " +
            "    AND c.id = :customerId", nativeQuery = true)
    List<Object[]> findDistinctByCustomerAndStatusAccepted(@Param("customerId") Long customerId);
}
