package com.THBS.cloudkitchenapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String status = "pending";

    private double price = 0.0;
    
    public OrderStatus() {}

    public OrderStatus(Long id, Order order, String status, double price) {
        this.id = id;
        this.order = order;
        this.status = status;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
    
    public void updatePrice(double price) {
        this.price = price;
    }
    

    @Override
    public String toString() {
        return "OrderStatus [id=" + id + ", order=" + order + ", status=" + status + ", price=" + price + "]";
    }

    
    // Constructors, getters, and setters
}