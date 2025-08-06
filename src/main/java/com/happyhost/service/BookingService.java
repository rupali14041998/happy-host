package com.happyhost.service;

import com.happyhost.exception.BookingNotFoundException;
import com.happyhost.model.Booking;
import com.happyhost.model.Status;
import com.happyhost.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking book(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBooking(String email) {
        return bookingRepository.findByEmail(email);
    }

    public void updateBooking(Booking booking) {
        Optional<Booking> bookingOptional = bookingRepository.findById(booking.getId());
        if (bookingOptional.isPresent()) {
            Booking booking1 = bookingOptional.get();
            booking1.setStatus(Status.RESCHEDULED);
            booking1.setEmail(booking.getEmail());
            booking1.setId(booking.getId());
            booking1.setTime(booking.getTime());
            booking1.setName(booking.getName());
            bookingRepository.save(booking1);
            return;
        }
        throw new BookingNotFoundException("Booking not found");
    }

    public void cancelBooking(Booking booking) {
        Optional<Booking> bookingOptional = bookingRepository.findById(booking.getId());
        if (bookingOptional.isPresent()) {
            Booking booking1 = bookingOptional.get();
            booking1.setStatus(Status.CANCELED);
            booking1.setEmail(booking.getEmail());
            booking1.setId(booking.getId());
            booking1.setTime(booking.getTime());
            booking1.setName(booking.getName());
            bookingRepository.save(booking1);
            return;
        }
        throw new BookingNotFoundException("Booking not found");
    }
}
