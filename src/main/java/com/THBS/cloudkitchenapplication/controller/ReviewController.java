package com.THBS.cloudkitchenapplication.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Review createrReview(@RequestBody Review user) {
        return ReviewService.createReview(user);
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
}
