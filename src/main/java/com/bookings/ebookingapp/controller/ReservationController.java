package com.bookings.ebookingapp.controller;

import com.bookings.ebookingapp.model.BookingRequest;
import com.bookings.ebookingapp.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public void createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        reservationService.createBooking(bookingRequest);
    }
}
