package com.THBS.cloudkitchenapplication.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.DTO.ReviewByRatingDTO;
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

    public List<Review> getReviewsByCatererId(Long catererId) {
        return reviewRepository.findByCatererId(catererId);
    }

    public List<ReviewByRatingDTO> getReviewsByRating() {
        List<Object[]> results = reviewRepository.findAllReviewsByRating();
        List<ReviewByRatingDTO> reviewDetailsDTO = new ArrayList<>();

        for (Object[] row : results) {
            String customerName = (String) row[0];
            String catererName = (String) row[1];
            Date reviewDateSql = (java.sql.Date) row[2];
            java.time.LocalDate reviewDate = reviewDateSql.toLocalDate(); // Convert java.sql.Date to
                                                                          // java.time.LocalDate
            double rating = (double) row[3]; // Assuming the rating is stored as an int in the database
            String review = (String) row[4];

            ReviewByRatingDTO ReviewDetails = new ReviewByRatingDTO(customerName, catererName, reviewDate, rating,
                    review);
            reviewDetailsDTO.add(ReviewDetails);
        }

        return reviewDetailsDTO;
    }

    public List<ReviewByRatingDTO> getReviewsRatingByCateterID(Long catererID) {
        List<Object[]> results = reviewRepository.findAllReviewsByRatingForCatererID(catererID);
        List<ReviewByRatingDTO> reviewDetailsDTO = new ArrayList<>();

        for (Object[] row : results) {
            String customerName = (String) row[0];
            String catererName = (String) row[1];
            Date reviewDateSql = (java.sql.Date) row[2];
            java.time.LocalDate reviewDate = reviewDateSql.toLocalDate(); // Convert java.sql.Date to java.time.LocalDate
            double rating = (double) row[3]; // Assuming the rating is stored as an int in the database
            String review = (String) row[4];

            ReviewByRatingDTO ReviewDetails = new ReviewByRatingDTO(customerName, catererName, reviewDate, rating, review);
            reviewDetailsDTO.add(ReviewDetails);
        }

        return reviewDetailsDTO;
    }

}
