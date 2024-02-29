package com.karthik.cloudkitchenapplication.service;

import com.karthik.cloudkitchenapplication.entity.Customer;
import com.karthik.cloudkitchenapplication.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer user) {
        return customerRepository.save(user);
    }

    public Customer updateCustomer(Long id, Customer user) {
        if (customerRepository.existsById(id)) {
            user.setId(id);
            return customerRepository.save(user);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public static Customer saveCustomer(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCustomer'");
    }
    
}
