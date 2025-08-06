package com.happyhost.repository;

import com.happyhost.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByEmail(String email);
}
