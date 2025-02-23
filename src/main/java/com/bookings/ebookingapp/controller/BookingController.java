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
        try {
            List<BookingRequest> bookings = bookingService.getAllBookings();
            if (bookings.isEmpty()) {
                throw new NotFoundException("No bookings found");
            }
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            logger.error("Error retrieving bookings", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving bookings");
        }
    }

    @GetMapping("/{bookingNumber}")
    public ResponseEntity<?> getBookingByNumber(@PathVariable int bookingNumber) {
        try {
            Optional<BookingRequest> booking = bookingService.getBookingByNumber(bookingNumber);
            if (booking.isEmpty()) {
                throw new NotFoundException("Booking number: " + bookingNumber + " not found");
            }
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            logger.error("Error retrieving booking reservation: " + bookingNumber, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving booking reservation: " + bookingNumber);
        }
    }

    @DeleteMapping("/{bookingNumber}")
    public ResponseEntity<?> deleteBookingByNumber(@PathVariable Integer bookingNumber) {
        try {
            bookingService.deleteBookingByNumber(bookingNumber);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            logger.error("Delete booking number: " + bookingNumber + " not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Delete booking number: " + bookingNumber + " not found");
        } catch (Exception e) {
            logger.error("Error deleting booking number: " + bookingNumber, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting booking number: " + bookingNumber);
        }
    }
}