package com.THBS.cloudkitchenapplication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.THBS.cloudkitchenapplication.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
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
    List<Map<String, Object>> findByOrderId(Long orderId);

    @Query(value = "SELECT " +
            "o.id AS orderId, " +
            "o.delivery_date AS deliveryDate, " +
            "o.number_of_people AS numberOfPeople, " +
            "o.caterer_id AS catererId, " +
            "o.customer_id AS customerId, " +
            "d.id AS dishId, " +
            "d.name AS dishName " +
            "FROM orders o " +
            "LEFT JOIN dish d ON o.id = d.order_id", nativeQuery = true)
    List<Map<String, Object>> findAllOrderDetails();

    @Query(value="SELECT " +
            "    o.id AS orderId, " +
            "    o.delivery_date AS deliveryDate, " +
            "    o.number_of_people AS numberOfPeople, " +
            "    d.name AS dishName, " +
            "    os.status AS orderStatus, " +
            "    os.price AS orderStatusPrice " +
            "FROM " +
            "    order o " +
            "LEFT JOIN " +
            "    o.dishes d " +
            "LEFT JOIN " +
            "    o.orderStatus os " +
            "WHERE " +
            "    o.customer.id = :customerId",nativeQuery=true)
    List<Map<String, Object>> findAllOrderDetailsByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "SELECT " +
            "    o.id AS order_id, " +
            "    c.full_name AS customer_name, " +
            "    ca.full_name AS caterer_name, " +
            "    o.delivery_date AS delivery_date, " +
            "    o.number_of_people AS number_of_people, " +
            "    os.price AS order_price, " +
            "    os.status AS order_status, " +
            "    od.id AS dish_id, " +
            "    od.name AS dish_name " +
            "FROM " +
            "    orders o " +
            "INNER JOIN " +
            "    order_status os ON o.id = os.order_id " +
            "INNER JOIN " +
            "    dish od ON o.id = od.order_id " +
            "INNER JOIN " +
            "    customer c ON o.customer_id = c.id " +
            "INNER JOIN " +
            "    caterer ca ON o.caterer_id = ca.id " +
            "WHERE " +
            "    o.customer_id = :customerId " +
            "    AND o.caterer_id = :catererId " +
            "GROUP BY " +
            "    o.id, c.full_name, ca.full_name, o.delivery_date, o.number_of_people, os.price, os.status, od.id, od.name", nativeQuery = true)
    List<Object[]> getOrdersWithDishesByCustomerAndCaterer(@Param("customerId") Long customerId,
            @Param("catererId") Long catererId);

}
