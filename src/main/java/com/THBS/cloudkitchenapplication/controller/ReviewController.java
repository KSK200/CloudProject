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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/Reviews")
public class ReviewController {
    @Autowired
    private ReviewService ReviewService;

    @Operation(summary = "This is used to get all the reviews")
    @GetMapping("/")
    public List<Review> getAllReviews() {
        return ReviewService.getAllReviews();
    }

    @Operation(summary = "This is used to create the Review for Caterer")
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

    @Operation(summary = "This is used to get Reviews Based on Caterer ID")
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

    @Operation(summary = "This is used to all the review given according to rating in Descending order")
    @GetMapping("/ratings")
    public List<ReviewByRatingDTO> getReviewsByRating() {
        return ReviewService.getReviewsByRating();
    }

    @Operation(summary = "This is used to all the all reviews of caterer based on catererID")
    @GetMapping("/ratings/{catererID}")
    public List<ReviewByRatingDTO> getReviewsRatingByCateterID(@PathVariable Long catererID) {
        return ReviewService.getReviewsRatingByCateterID(catererID);
    }
}
