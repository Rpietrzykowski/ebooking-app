package com.bookings.ebookingapp.steps;

import com.bookings.ebookingapp.model.BookingRequest;
import com.bookings.ebookingapp.repository.BookingRequestRepository;
import com.bookings.ebookingapp.service.ReservationService;
import com.bookings.ebookingapp.utils.BookingKafkaListener;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration
public class BookingSteps {

    @Autowired
    ReservationService reservationService;

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    private final BookingRequest bookingRequest = new BookingRequest();

    private final BookingKafkaListener bookingKafkaListener = new BookingKafkaListener();

    @Given("^a booking request JSON with booking number (.*) name (.*) and items (.*)$")
    public void aBookingRequestJSONWithBookingNumberBookingNumberNameNameAndItemsItems(Integer bookingNumber, String name, Integer items) {
        bookingRequest.setBookingNumber(bookingNumber);
        bookingRequest.setName(name);
        bookingRequest.setItems(items);
    }

    @When("^the booking request is send$")
    public void the_booking_request_is_send() {
        reservationService.createBooking(bookingRequest);
    }

    @When("^the booking message is consumed$")
    public void the_booking_message_is_consumed() throws InterruptedException {
        boolean messageConsumed = bookingKafkaListener.getLatch().await(10, TimeUnit.SECONDS);
        assertThat(messageConsumed).isTrue();
        assertThat(bookingKafkaListener.getPayload()).isEqualTo(bookingRequest);
    }

    @Then("^the booking reservation (.*) should be saved in MongoDB with name (.*) and items (.*)$")
    public void the_booking_request_should_be_saved_in_mongodb(Integer bookingNumber, String name, Integer items) {
        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingNumber).orElse(null);
        assertThat(bookingRequest).isNotNull();
        assertThat(bookingRequest.getName()).isEqualTo(name);
        assertThat(bookingRequest.getItems()).isEqualTo(items);
    }
}
