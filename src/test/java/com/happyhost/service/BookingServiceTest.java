package com.happyhost.service;

import com.happyhost.exception.BookingNotFoundException;
import com.happyhost.model.Booking;
import com.happyhost.model.Status;
import com.happyhost.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Test
    void testBook() {
        Mockito.when(bookingRepository.save(Mockito.any())).thenReturn(new Booking());
        var response = bookingService.book(new Booking());
        assertNotNull(response);
    }

    @Test
    void testGetAllBooking() {
        Booking booking = new Booking();
        booking.setName("test");
        booking.setEmail("test@gmail.com");
        var bookingList = new ArrayList<Booking>();
        bookingList.add(booking);
        Mockito.when(bookingRepository.findByEmail(Mockito.anyString())).thenReturn(bookingList);
        var response = bookingService.getAllBooking("test@gmail.com");
        assertTrue(!response.isEmpty());
    }

    @Test
    void testUpdateBookingSuccess() {
        // Given
        Booking booking = new Booking();
        booking.setId(1l);
        booking.setEmail("test@example.com");
        booking.setTime(LocalDateTime.now().toString());
        booking.setName("test");

        Booking existingBooking = new Booking();
        existingBooking.setId(1l);

        Mockito.when(bookingRepository.findById(1l))
                .thenReturn(Optional.of(existingBooking));

        // When
        bookingService.updateBooking(booking);

        // Then
        Mockito.verify(bookingRepository).save(Mockito.argThat(savedBooking ->
                savedBooking.getStatus() == Status.RESCHEDULED &&
                        savedBooking.getEmail().equals("test@example.com") &&
                        savedBooking.getName().equals("test")
        ));
    }

    @Test
    void testUpdateBookingNotFound() {
        Booking booking = new Booking();
        booking.setId(101l);

        Mockito.when(bookingRepository.findById(101l))
                .thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.updateBooking(booking);
        });
    }

    @Test
    void testCancelBookingSuccess() {

        Booking inputBooking = new Booking();
        inputBooking.setId(1l);
        inputBooking.setEmail("cancel@example.com");
        inputBooking.setName("John Doe");
        inputBooking.setTime(LocalDateTime.now().toString());

        Booking existingBooking = new Booking();
        existingBooking.setId(1l);
        existingBooking.setStatus(Status.CANCELLED); // initial status

        Mockito.when(bookingRepository.findById(1L))
                .thenReturn(Optional.of(existingBooking));
        bookingService.cancelBooking(inputBooking);
        Mockito.verify(bookingRepository).save(Mockito.argThat(saved ->
                saved.getId().equals(1l) &&
                        saved.getStatus() == Status.CANCELLED &&
                        saved.getEmail().equals("cancel@example.com") &&
                        saved.getName().equals("John Doe")
        ));
    }

    @Test
    void testCancelBookingNotFound() {
        Booking inputBooking = new Booking();
        inputBooking.setId(999L); // non-existent ID

        Mockito.when(bookingRepository.findById(999L))
                .thenReturn(Optional.empty());
        BookingNotFoundException exception = assertThrows(BookingNotFoundException.class, () -> {
            bookingService.cancelBooking(inputBooking);
        });
        assertEquals("Booking not found", exception.getMessage());
    }


}
