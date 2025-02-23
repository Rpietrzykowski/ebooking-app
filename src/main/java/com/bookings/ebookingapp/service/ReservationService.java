package com.bookings.ebookingapp.service;

import com.bookings.ebookingapp.model.BookingRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "TEST.BOOKINGREQUEST.TOPIC";

    @Autowired
    public ReservationService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createBooking(BookingRequest bookingRequest) {
        logger.info("Creating booking: {}", bookingRequest);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String message = objectMapper.writeValueAsString(bookingRequest);
            kafkaTemplate.send(TOPIC, message);
        } catch (JsonProcessingException e) {
            logger.error("Error processing booking request: {}", bookingRequest, e);
        }
    }
}
