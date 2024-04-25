package com.THBS.cloudkitchenapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.THBS.cloudkitchenapplication.entity.Caterer;

@Repository
public interface CatererRepository extends JpaRepository<Caterer, Long> {

    Caterer findByUsername(String username);

    @Query(value = "SELECT o.id AS order_id, " +
            "c.full_name AS customer_name, " +
            "c.phonenumber AS customer_phonenumber, " +
            "c.email AS customer_email, " +
            "o.delivery_date AS delivery_date, " +
            "o.number_of_people AS number_of_people, " +
            "od.id AS dish_id, " +
            "od.name AS dish_name " +
            "FROM orders o " +
            "INNER JOIN order_status os ON o.id = os.order_id " +
            "INNER JOIN dish od ON o.id = od.order_id " +
            "INNER JOIN customer c ON o.customer_id = c.id " +
            "INNER JOIN caterer ca ON o.caterer_id = ca.id " +
            "WHERE o.caterer_id = :catererId", nativeQuery = true)
    List<Object[]> getOrdersWithDishesByCaterer(@Param("catererId") Long catererId);

}