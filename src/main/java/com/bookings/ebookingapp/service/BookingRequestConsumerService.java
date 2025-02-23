package com.bookings.ebookingapp.service;

import com.bookings.ebookingapp.model.BookingRequest;
import com.bookings.ebookingapp.repository.BookingRequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingRequestConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(BookingRequestConsumerService.class);

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @KafkaListener(topics = "TEST.BOOKINGREQUEST.TOPIC", groupId = "booking-group")
    public void consume(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BookingRequest bookingRequest = objectMapper.readValue(message, BookingRequest.class);
            bookingRequestRepository.save(bookingRequest);
        } catch (Exception e) {
            logger.error("Error processing booking request message: {}", message, e);
        }
    }
}
