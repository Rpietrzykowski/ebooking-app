package com.bookings.ebookingapp.controller;

import com.bookings.ebookingapp.exception.NotFoundException;
import com.bookings.ebookingapp.model.BookingRequest;
import com.bookings.ebookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllBookings() {
            List<BookingRequest> bookings = bookingService.getAllBookings();
            return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{bookingNumber}")
    public ResponseEntity<?> getBookingByNumber(@PathVariable int bookingNumber) {
        Optional<BookingRequest> booking = bookingService.getBookingByNumber(bookingNumber);
        if (booking.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking number: " + bookingNumber + " was not found");
        }
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{bookingNumber}")
    public ResponseEntity<?> deleteBookingByNumber(@PathVariable Integer bookingNumber) {
        try {
            bookingService.deleteBookingByNumber(bookingNumber);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            logger.error("Delete booking number: {} not found", bookingNumber, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Delete booking number: " + bookingNumber + " not found");
        }
    }
}