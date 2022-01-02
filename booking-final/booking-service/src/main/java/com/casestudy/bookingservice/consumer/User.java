package com.casestudy.bookingservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.casestudy.bookingservice.config.MessagingConfig;
import com.casestudy.bookingservice.models.BookingStatus;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(BookingStatus bookingStatus) {
        System.out.println("Message recieved from queue : " + bookingStatus);
    }
}
