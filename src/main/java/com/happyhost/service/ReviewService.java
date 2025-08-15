package com.happyhost.service;

import com.happyhost.model.Review;
import com.happyhost.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    public void submitReview(Review review) {
        reviewsRepository.save(review);
    }

    public List<Review> getReviewsByEmail(String email) {
        return reviewsRepository.findByEmail(email);
    }

    public void deleteReview(Long id) {
        reviewsRepository.deleteById(id);
    }
}
