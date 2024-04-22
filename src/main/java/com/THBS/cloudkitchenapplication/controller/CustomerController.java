package com.THBS.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.THBS.cloudkitchenapplication.DTO.CustomerOrderDTO;
import com.THBS.cloudkitchenapplication.DTO.OrderDetailsDTO;
import com.THBS.cloudkitchenapplication.entity.Customer;
import com.THBS.cloudkitchenapplication.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService userService;

    @Operation(summary  = "This will list out all the customers")
    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @Operation(summary  = "This will fetch the customer details based on CustomerID")
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return userService.getCustomerById(id);
    }

    
    @Operation(summary  = "This is used for customer signup/register")
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Customer user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userService.createCustomer(user);
        return new ResponseEntity<>("User signed up successfully!", HttpStatus.CREATED);
    }
    @Operation(summary  = "This is used to update customer details")
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer user) {
        return userService.updateCustomer(id, user);
    }
    @Operation(summary  = "This is used to delete the customer by the id")
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
    }
    @Operation(summary  = "This is used to fetch all orders based on order-id")
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(@PathVariable Long orderId) {
        List<OrderDetailsDTO> orderDetails = userService.findByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetails.get(0)); // Assuming the list contains only one order
    }
    @Operation(summary  = "This is used to fecth the all orders")
    @GetMapping("/allorders")
    public ResponseEntity<List<OrderDetailsDTO>> findAllOrderDetail() {
        List<OrderDetailsDTO> orderDetails = userService.findAllOrderDetails();
        if (orderDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetails);
    }

    @Operation(summary  = "This will will list the all the orders and its order-status based on customer_ID and caterer_ID")
    @GetMapping("/orderdetails/{customerId}/{catererId}")
    public List<CustomerOrderDTO> getOrdersByCustomerAndCaterer(
            @PathVariable("customerId") Long customerId,
            @PathVariable("catererId") Long catererId) {
        return userService.getOrdersByCustomerAndCaterer(customerId, catererId);
    }

}
