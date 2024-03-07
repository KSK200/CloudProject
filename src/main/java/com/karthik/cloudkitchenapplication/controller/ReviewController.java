package com.karthik.cloudkitchenapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.karthik.cloudkitchenapplication.entity.Review;
import com.karthik.cloudkitchenapplication.service.ReviewService;

@RestController
@RequestMapping("/reviews")
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
    

}
