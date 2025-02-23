package com.bookings.ebookingapp.controller;

import com.bookings.ebookingapp.model.BookingRequest;
import com.bookings.ebookingapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public void createBooking(@RequestBody BookingRequest bookingRequest) {
        reservationService.createBooking(bookingRequest);
    }
}
