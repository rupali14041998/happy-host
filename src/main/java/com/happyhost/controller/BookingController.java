package com.happyhost.controller;

import com.happyhost.model.Booking;
import com.happyhost.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public Booking book(@RequestBody Booking booking) {
        return bookingService.book(booking);
    }

    @GetMapping("/getAllBookings/{email}")
    public List<Booking> getAllBookings(@PathVariable("email") String email) {
        return bookingService.getAllBooking(email);
    }

    public ResponseEntity<String> updateBooking(@RequestBody Booking booking) {
        bookingService.updateBooking(booking);
        return new ResponseEntity<>("Booking is updated", HttpStatus.OK);
    }

    public ResponseEntity<String> cancelBooking(@RequestBody Booking booking) {
        bookingService.cancelBooking(booking);
        return new ResponseEntity<>("Booking is cancelled", HttpStatus.OK);
    }
}
