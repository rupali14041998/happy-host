package com.happyhost.controller;

import com.happyhost.model.Review;
import com.happyhost.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @Test
    void testSubmitReview() {
        Mockito.doNothing().when(reviewService).submitReview(Mockito.any());
        var response = reviewController.submitReview(new Review());
        Mockito.verify(reviewService, Mockito.times(1)).submitReview(Mockito.any());
        assert response.getStatusCode().is2xxSuccessful();
    }

    @Test
    void testGetReviewsByEmail() {
        Mockito.when(reviewService.getReviewsByEmail(Mockito.anyString())).thenReturn(List.of(new Review()));
        var response = reviewController.getReviewsByEmail("test@gmail.com");
        Mockito.verify(reviewService, Mockito.times(1)).getReviewsByEmail(Mockito.anyString());
        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody().size() == 1;
    }

    @Test
    void testDeleteReview() {
        Mockito.doNothing().when(reviewService).deleteReview(Mockito.anyLong());
        var response = reviewController.deleteReview(1L);
        Mockito.verify(reviewService, Mockito.times(1)).deleteReview(Mockito.anyLong());
        assert response.getStatusCode().is2xxSuccessful();
    }
}
