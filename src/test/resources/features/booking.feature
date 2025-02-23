Feature: Booking Request

  Scenario: Save booking request from Kafka topic
    Given a booking request JSON
    When the booking request is consumed
    Then the booking request should be saved in MongoDB