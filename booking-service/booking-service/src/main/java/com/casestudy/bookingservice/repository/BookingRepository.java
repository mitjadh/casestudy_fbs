package com.casestudy.bookingservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.bookingservice.models.Booking;

public interface BookingRepository extends MongoRepository<Booking,Integer>{

	List<Booking> findById(String id);

	 void deleteById(String id);

}
