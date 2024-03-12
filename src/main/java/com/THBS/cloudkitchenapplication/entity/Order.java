package com.THBS.cloudkitchenapplication.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate deliveryDate;
    private int numberOfPeople;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "caterer_id")
    private Caterer caterer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderStatus orderStatus;

    @JsonIgnore
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentStatus paymentStatus;


    public Order(){}


    public Order(Long id, LocalDate deliveryDate, int numberOfPeople, Customer customer, Caterer caterer,
            List<Dish> dishes, OrderStatus orderStatus, PaymentStatus paymentStatus) {
        this.id = id;
        this.deliveryDate = deliveryDate;
        this.numberOfPeople = numberOfPeople;
        this.customer = customer;
        this.caterer = caterer;
        this.dishes = dishes;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }


    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public int getNumberOfPeople() {
        return numberOfPeople;
    }


    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }


    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Caterer getCaterer() {
        return caterer;
    }


    public void setCaterer(Caterer caterer) {
        this.caterer = caterer;
    }


    public List<Dish> getDishes() {
        return dishes;
    }


    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }


    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }


    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    @Override
    public String toString() {
        return "Order [id=" + id + ", deliveryDate=" + deliveryDate + ", numberOfPeople=" + numberOfPeople
                + ", customer=" + customer + ", caterer=" + caterer + ", dishes=" + dishes + ", orderStatus="
                + orderStatus + ", paymentStatus=" + paymentStatus + "]";
    }


    // Constructors, getters, and setters
}
