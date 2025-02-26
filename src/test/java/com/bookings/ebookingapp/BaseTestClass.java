package com.bookings.ebookingapp;

import com.bookings.ebookingapp.controller.ReservationController;
import io.cucumber.java.Before;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.bookings.ebookingapp:local-server:+:stubs:8080"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class BaseTestClass {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new ReservationController());
    }
}
