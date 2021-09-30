package com.casestudy.searchservice.repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.casestudy.searchservice.models.Flights;

@Repository
	public interface FlightRepository extends MongoRepository<Flights,Integer>{
		
		@Query("{'from_city' : ?0, 'to_city' : ?1 , 'date_depart' : ?2 }")
		List<Flights> findByLocation(String from_city, String to_city, String date_depart);
	}