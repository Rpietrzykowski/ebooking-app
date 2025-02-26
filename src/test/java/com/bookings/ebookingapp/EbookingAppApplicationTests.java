package com.bookings.ebookingapp;

import com.bookings.ebookingapp.model.BookingRequest;
import com.bookings.ebookingapp.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class EbookingAppApplicationTests {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void testCreateBooking() {
//        BookingRequest bookingRequest = new BookingRequest();
//        bookingRequest.setBookingNumber(123);
//        bookingRequest.setName("John Doe");
//        bookingRequest.setItems(5);
//
//        reservationService.createBooking(bookingRequest);
//
//        verify(kafkaTemplate, times(1)).send(anyString(), anyString());
    }
}
