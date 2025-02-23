package com.bookings.ebookingapp.repository;

import com.bookings.ebookingapp.model.BookingRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookingRequestRepository extends MongoRepository<BookingRequest, Integer> {
    Optional<BookingRequest> findByBookingNumber(int bookingNumber);
}