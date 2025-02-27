package com.bookings.ebookingapp.contracttests;

import com.bookings.ebookingapp.BaseTestClass;
import com.bookings.ebookingapp.model.BookingRequest;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ReservationContractTests extends BaseTestClass {

    @Test
    public void validate_shouldSendTheCorrectReservation() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setBookingNumber(10);
        bookingRequest.setName("Robert Pietrzykowski");
        bookingRequest.setItems(1);


        // given:
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/json")
                .body(bookingRequest.toString());

        // when:
        ResponseOptions<MockMvcResponse> response = given().spec(request)
                .post("/api/reservations/create");

        // then:
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.header("Content-Type")).matches("application/json");
        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThatJson(parsedJson).field("['bookingNumber']").isEqualTo(10);
    }
}
