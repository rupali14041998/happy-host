package com.happyhost.service;

import com.happyhost.model.Review;
import com.happyhost.repository.ReviewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewsRepository reviewsRepository;

    @Test
    void testSubmitReview() {
        Mockito.when(reviewsRepository.save(Mockito.any())).thenReturn(null);
        reviewService.submitReview(new Review());
        Mockito.verify(reviewsRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void testGetReviewsByEmail() {
        Mockito.when(reviewsRepository.findByEmail(Mockito.anyString())).thenReturn(List.of(new Review()));
        var reviews = reviewService.getReviewsByEmail("test@gmail.com");
        Mockito.verify(reviewsRepository, Mockito.times(1)).findByEmail(Mockito.anyString());
        assert reviews.size() == 1;
    }

    @Test
    void testDeleteReview() {
        Mockito.doNothing().when(reviewsRepository).deleteById(Mockito.anyLong());
        reviewService.deleteReview(1L);
        Mockito.verify(reviewsRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}
