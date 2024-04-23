package com.THBS.cloudkitchenapplication.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.THBS.cloudkitchenapplication.DTO.ReviewByRatingDTO;
import com.THBS.cloudkitchenapplication.DTO.ReviewDTO;
import com.THBS.cloudkitchenapplication.entity.Review;
import com.THBS.cloudkitchenapplication.service.ReviewService;

@RestController
@RequestMapping("/Reviews")
public class ReviewController {
    @Autowired
    private ReviewService ReviewService;

    @GetMapping("/")
    public List<Review> getAllReviews() {
        return ReviewService.getAllReviews();
    }

    @PostMapping("/")
    public ResponseEntity<String> createReview(@RequestBody Review review) {
        // Assuming ReviewService.createReview returns the created review
        Review createdReview = ReviewService.createReview(review);
        if (createdReview != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Review has been given successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create review");
        }
    }

    @GetMapping("/{catererId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByCatererId(@PathVariable Long catererId) {
        List<Review> reviews = ReviewService.getReviewsByCatererId(catererId);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ReviewDTO> reviewDTOs = reviews.stream()
                .map(review -> {
                    ReviewDTO reviewDTO = new ReviewDTO();
                    reviewDTO.setId(review.getId());
                    reviewDTO.setCatererId(review.getCaterer().getId());
                    reviewDTO.setCustomerId(review.getCustomer().getId());
                    reviewDTO.setReview(review.getReview());
                    reviewDTO.setRating(review.getRating());
                    reviewDTO.setReviewDate(review.getReviewDate());
                    return reviewDTO;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(reviewDTOs);
    }

    @GetMapping("/ratings")
    public List<ReviewByRatingDTO> getReviewsByRating() {
        return ReviewService.getReviewsByRating();
    }
}
