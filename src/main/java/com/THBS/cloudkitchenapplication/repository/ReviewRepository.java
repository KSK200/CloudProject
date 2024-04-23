package com.THBS.cloudkitchenapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.THBS.cloudkitchenapplication.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCatererId(Long catererId);

    @Query(value = "SELECT c.full_name AS customer_username, ca.username AS caterer_username, r.review_date, r.rating, r.review "
            +
            "FROM review r " +
            "INNER JOIN customer c ON r.customer_id = c.id " +
            "INNER JOIN caterer ca ON r.caterer_id = ca.id " +
            "ORDER BY r.rating DESC", nativeQuery = true)
    List<Object[]> findAllReviewsByRating();

    
    @Query(value = "SELECT c.full_name AS customer_username, ca.username AS caterer_username, r.review_date, r.rating, r.review "
            +
            "FROM review r " +
            "INNER JOIN customer c ON r.customer_id = c.id " +
            "INNER JOIN caterer ca ON r.caterer_id = ca.id " +
            "WHERE ca.id = :catererId " +
            "ORDER BY r.rating DESC", nativeQuery = true)
    List<Object[]> findAllReviewsByRatingForCatererID(@Param("catererId") Long catererId);
}