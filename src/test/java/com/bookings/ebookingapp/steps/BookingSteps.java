package com.bookings.ebookingapp.steps;

import com.bookings.ebookingapp.model.BookingRequest;
import com.bookings.ebookingapp.repository.BookingRequestRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class BookingSteps {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    private String bookingRequestJson;

    @Given("a booking request JSON")
    public void a_booking_request_json() {
        bookingRequestJson = "{ \"bookingnumber\":222222, \"name\": \"John\", \"items\": 3 }";
    }

    @When("the booking request is consumed")
    public void the_booking_request_is_consumed() {
        kafkaTemplate.send("TEST.BOOKINGREQUEST.TOPIC", bookingRequestJson);
    }

    @Then("the booking request should be saved in MongoDB")
    public void the_booking_request_should_be_saved_in_mongodb() {
        // Add assertions to verify the booking request is saved in MongoDB
        BookingRequest bookingRequest = bookingRequestRepository.findById(22222).orElse(null);
        assert bookingRequest != null;
        assert bookingRequest.getName().equals("John");
        assert bookingRequest.getItems() == 3;
    }
}
