package com.THBS.cloudkitchenapplication.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long catererId;
    private Long customerId;
    private String review;
    private double rating;
    private LocalDate reviewDate;




    // Getters and setters for all fields
    // Constructor(s) if needed
}

