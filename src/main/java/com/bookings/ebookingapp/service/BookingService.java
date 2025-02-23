package com.bookings.ebookingapp.service;

import com.bookings.ebookingapp.exception.NotFoundException;
import com.bookings.ebookingapp.model.BookingRequest;
import com.bookings.ebookingapp.repository.BookingRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
    private final BookingRequestRepository bookingRequestRepository;

    @Autowired
    public BookingService( BookingRequestRepository bookingRequestRepository) {
        this.bookingRequestRepository = bookingRequestRepository;
    }

    public List<BookingRequest> getAllBookings() {
        List<BookingRequest> bookings = bookingRequestRepository.findAll();
        logger.info("Retrieved bookings: {}", bookings);
        return bookings;
    }

    public Optional<BookingRequest> getBookingByNumber(int bookingNumber) {
        logger.info("Searching for booking with number: {}", bookingNumber);
        Optional<BookingRequest> booking = bookingRequestRepository.findByBookingNumber(bookingNumber);
        logger.info("Found booking: {}", booking);
        return booking;
    }

    public void deleteBookingByNumber(int bookingNumber) {
        if (!bookingRequestRepository.existsById(bookingNumber)) {
            throw new NotFoundException("Booking number: " + bookingNumber + " not found");
        }
        bookingRequestRepository.deleteById(bookingNumber);
    }
}
