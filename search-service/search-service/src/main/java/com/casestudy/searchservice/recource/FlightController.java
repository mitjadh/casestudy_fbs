package com.casestudy.searchservice.recource;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.searchservice.models.Flights;
import com.casestudy.searchservice.repository.FlightRepository;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/searchflights")
public class FlightController {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@RequestMapping(value="/getflights",method=RequestMethod.GET)
	public List<Flights> getAll(){
		return flightRepository.findAll();
	}
	
	@RequestMapping(value="/addflights",method=RequestMethod.POST)
	public void addFlight(@RequestBody Flights flights) {
		flightRepository.insert(flights);
	}
	
	@RequestMapping(value="/flights/{from_city}/{to_city}/{date_depart}",method=RequestMethod.GET)
	public List<Flights> getFlight(@PathVariable(value="from_city")String from_city,
			@PathVariable(value="to_city")String to_city,@PathVariable(value="date_depart")String date_depart){
		return flightRepository.findByLocation(from_city,to_city,date_depart);
	}
	
	@RequestMapping(value="/flights/{flight_id}",method=RequestMethod.GET)
	public Optional<Flights> getFlightById(@PathVariable int flight_id){
		return flightRepository.findById(flight_id);
	}
	
	
	/*@RequestMapping(value="/flights/{from_city}/{to_city}/{date_dept}",method=RequestMethod.GET)
	public Optional<Flights> getFlight(@PathVariable(value="from_city") String from_city,
		@PathVariable(value="to_city") String to_city,@PathVariable(value="date_depart") String date_depart){
			return flightRepository.findByCity(from_city,to_city,date_depart);
		}*/
	}

