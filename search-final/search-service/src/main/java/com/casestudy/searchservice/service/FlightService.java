package com.casestudy.searchservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.casestudy.searchservice.exception.FlightAlreadyExistsException;
import com.casestudy.searchservice.exception.FlightNotFoundException;
import com.casestudy.searchservice.models.Flights;

public interface FlightService {
	
	public List<Flights> getAllFlight() throws FlightNotFoundException;
	
	public List<Flights> getFlight(String from_city,String to_city,String date_depart) throws FlightNotFoundException;
	
	ResponseEntity<Flights> addFlight(Flights flights) throws FlightAlreadyExistsException;
	
	ResponseEntity<?> updateFlight(int flight_id, Flights flights);

	ResponseEntity<?> deleteFlight(int flight_id) throws FlightNotFoundException;
}
