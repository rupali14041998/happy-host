package com.happyhost.controller;

import com.happyhost.model.Review;
import com.happyhost.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitReview(@RequestBody Review review) {
        reviewService.submitReview(review);
        return ResponseEntity.ok("Review submitted successfully");
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Review>> getReviewsByEmail(@PathVariable String email) {
        List<Review> reviews = reviewService.getReviewsByEmail(email);
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
