package com.THBS.cloudkitchenapplication.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Caterer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String address;
    private String phoneNumber;
    private String role;
    private String username;
    private String password;

    @OneToMany(mappedBy = "caterer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "caterer")
    private List<AccountDetails> paymentDetails;

    // @OneToMany(mappedBy = "caterer", cascade = CascadeType.ALL)
    // private List<Review> reviews = new ArrayList<>();
    
    
}
