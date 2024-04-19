package com.THBS.cloudkitchenapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.THBS.cloudkitchenapplication.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
    Customer findByUsername(String username);

    @Query(value = "SELECT " +
        "o.id AS orderId, " +
        "o.delivery_date AS deliveryDate, " +
        "o.number_of_people AS numberOfPeople, " +
        "o.caterer_id AS catererId, " +
        "o.customer_id AS customerId, " +
        "d.id AS dishId, " +
        "d.name AS dishName " +
        "FROM orders o " +
        "LEFT JOIN dish d ON o.id = d.order_id " +
        "WHERE o.id = :orderId", nativeQuery = true)
List<Object[]> findByOrderId(Long orderId);

}
