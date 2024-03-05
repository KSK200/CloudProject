package com.karthik.cloudkitchenapplication.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "caterer_id")
    private Caterer caterer;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    private String review;
    private int rating;
    private LocalDateTime reviewDate;

    public Review() {}

    public Review(Long id, Caterer caterer, Customer customer, String review, int rating, LocalDateTime reviewDate) {
        this.id = id;
        this.caterer = caterer;
        this.customer = customer;
        this.review = review;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Caterer getCaterer() {
        return caterer;
    }
    public void setCaterer(Caterer caterer) {
        this.caterer = caterer;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public LocalDateTime getReviewDate() {
        return reviewDate;
    }
    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
    @Override
    public String toString() {
        return "Review [id=" + id + ", caterer=" + caterer + ", customer=" + customer + ", review=" + review
                + ", rating=" + rating + ", reviewDate=" + reviewDate + "]";
    }

    
}
