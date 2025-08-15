package com.happyhost.repository;

import com.happyhost.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Long> {

    List<Review> findByEmail(String email);
    // This interface will automatically provide CRUD operations for Review entities
    // No additional methods are needed unless custom queries are required
}
