package com.casestudy.bookingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingStatus {

    private Booking booking;
    private String status;//progress,completed
    private String message;
}
