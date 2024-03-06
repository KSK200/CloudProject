package com.karthik.cloudkitchenapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.cloudkitchenapplication.entity.Review;
import com.karthik.cloudkitchenapplication.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @SuppressWarnings("null")
    public Review createReview(Review user) {
        return reviewRepository.save(user);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    
}
