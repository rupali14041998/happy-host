package com.happyhost.controller;

import com.happyhost.model.Booking;
import com.happyhost.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @Test
    void testBook() {
        Mockito.when(bookingService.book(Mockito.any())).thenReturn(new Booking());
        var response = bookingController.book(new Booking());
        assertTrue(true);
    }

    @Test
    void testGetAllBookings() {
        Booking booking = new Booking();
        booking.setName("test");
        booking.setEmail("test@gmail.com");
        booking.setTime("11:00 AM");
        var list = new ArrayList<Booking>();
        list.add(booking);
        Mockito.when(bookingService.getAllBooking(Mockito.anyString())).thenReturn(list);
        var response = bookingController.getAllBookings("test@gmail.com");
        assertTrue(!response.isEmpty());
    }

    @Test
    void testUpdateBooking() {
        Mockito.doNothing().when(bookingService).updateBooking(Mockito.any());
        var response = bookingController.updateBooking(new Booking());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testCancelBooking() {
        Mockito.doNothing().when(bookingService).cancelBooking(new Booking());
        var response = bookingController.cancelBooking(new Booking());
        assertEquals(200, response.getStatusCode().value());
    }
}
