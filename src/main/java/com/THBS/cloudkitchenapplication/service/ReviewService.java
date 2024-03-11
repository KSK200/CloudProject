package com.THBS.cloudkitchenapplication.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.THBS.cloudkitchenapplication.entity.Review;
import com.THBS.cloudkitchenapplication.repository.ReviewRepository;

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

    // public List<Review> find(Long catererId) {
    //     return reviewRepository.findByCatererId(catererId);
    // }

    public List<Review> getReviewsByCatererId(Long catererId) {
        return reviewRepository.findByCatererId(catererId);
    }

    
}
