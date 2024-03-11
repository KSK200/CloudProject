package com.THBS.cloudkitchenapplication.DTO;

import java.time.LocalDateTime;

public class ReviewDTO {
    private Long id;
    private Long catererId;
    private Long customerId;
    private String review;
    private int rating;
    private LocalDateTime reviewDate;

    public ReviewDTO(){}

    public ReviewDTO(Long id, Long catererId, Long customerId, String review, int rating, LocalDateTime reviewDate) {
        this.id = id;
        this.catererId = catererId;
        this.customerId = customerId;
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

    public Long getCatererId() {
        return catererId;
    }

    public void setCatererId(Long catererId) {
        this.catererId = catererId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
        return "ReviewDTO [id=" + id + ", catererId=" + catererId + ", customerId=" + customerId + ", review=" + review
                + ", rating=" + rating + ", reviewDate=" + reviewDate + "]";
    }

    

    // Getters and setters for all fields
    // Constructor(s) if needed
}

