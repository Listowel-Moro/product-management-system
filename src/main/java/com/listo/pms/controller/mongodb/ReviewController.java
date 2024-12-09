package com.listo.pms.controller.mongodb;

import com.listo.pms.model.mongodb.Review;
import com.listo.pms.service.mongodb.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{productId}")
    public ResponseEntity<Review> addReview(@PathVariable String productId, @RequestBody Review review) {
        review.setProductId(productId);
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable String productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
    }
}