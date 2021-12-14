package com.casestudy.searchservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.casestudy.searchservice.exception.FlightAlreadyExistsException;
import com.casestudy.searchservice.exception.FlightNotFoundException;
import com.casestudy.searchservice.models.Flights;
import com.casestudy.searchservice.repository.FlightRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	private FlightRepository flightRepository; 
	
	@Override
	public List<Flights> getAllFlight() {
		
		log.warn("Please provide the correct url");
		log.info("All Flight will be displayed in browser");
		
		return (List<Flights>) flightRepository.findAll();
	}
	
	@Override
	public List<Flights> getFlight(@PathVariable("from_city") String from_city, @PathVariable("to_city") String to_city,
			@PathVariable("date_depart") String date_depart) throws FlightNotFoundException{
		
	log.info("Flight by "+ from_city + " " + to_city + " " + date_depart);
	
		if(flightRepository.findByLocation(from_city, to_city, date_depart).isEmpty()){ 
			throw new FlightNotFoundException();
		}else {
		return flightRepository.findByLocation(from_city,to_city,date_depart);
		}
	}
	
	
	@Override
	public ResponseEntity<Flights> addFlight(Flights flights) throws FlightAlreadyExistsException {
		// TODO Auto-generated method stub
		log.info("Adding new flight to database " + flights.getFlight_id() + " " + " " + flights.getFlight_name() 
		+ " " + flights.getFrom_city() + " " + flights.getTo_city() + " " + flights.getDate_depart() + " " 
		+ flights.getTime_arrival() + " " + flights.getTime_depart() + " " + flights.getSeats() + " " + flights.getFare());
			
		if(flightRepository.existsById(flights.getFlight_id())) {
			throw new FlightAlreadyExistsException();
			}
				//Flight flg = flightRepository.insert(flight);
			     //return flg;
			flightRepository.insert(flights);
			return new ResponseEntity("New Flight added successfully", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> updateFlight (@PathVariable int flight_id,@RequestBody Flights flights) {
		
		log.info("flight updated with id "+ flight_id);
		
		flights.setFlight_id(flight_id);
		flightRepository.save(flights);
		return new ResponseEntity<>("flight updated successfully",HttpStatus.ACCEPTED);
		
	}
	
	@Override
	public ResponseEntity<?> deleteFlight(@PathVariable int flight_id) throws FlightNotFoundException{
		
		log.info("flight deleted with id "+ flight_id);

		if(flightRepository.findById(flight_id).isEmpty() ) {
			throw new FlightNotFoundException();
		}
		flightRepository.deleteById(flight_id);
		return new ResponseEntity<>("flight deleted successfully",HttpStatus.ACCEPTED);
	}
}

