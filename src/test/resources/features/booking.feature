Feature: Booking Request

  Scenario Outline: Save booking request from Kafka topic
    Given a booking request JSON with booking number <bookingNumber> name <name> and items <items>
    When the booking request is send
    Then the booking reservation <bookingNumber> should be saved in MongoDB with name <name> and items <items>

    Examples:
      | bookingNumber | name | items |
      | 1             | John | 2     |
      | 2             | Jane | 3     |