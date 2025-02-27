package com.bookings.ebookingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

@Document(collection = "bookingrequest")
public class BookingRequest {
    @Id
    @NotNull
    @Min(1)
    private Integer bookingNumber;

    @NotBlank
    private String name;

    @Min(1)
    private Integer items;

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "bookingNumber=" + bookingNumber +
                " name='" + name + '\'' +
                " items=" + items +
                '}';
    }
}

