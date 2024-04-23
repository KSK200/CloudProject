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
public class ReviewByRatingDTO {

    private String customerUsername;
    private String catererUsername;
    private LocalDate reviewDate;
    private double rating;
    private String review;

}
