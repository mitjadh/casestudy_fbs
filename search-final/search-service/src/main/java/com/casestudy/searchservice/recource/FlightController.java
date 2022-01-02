/*package com.casestudy.searchservice.recource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.casestudy.searchservice.exception.FlightAlreadyExistsException;
import com.casestudy.searchservice.exception.FlightNotFoundException;
import com.casestudy.searchservice.models.Flights;
import com.casestudy.searchservice.repository.FlightRepository;
import com.casestudy.searchservice.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/searchflights")
public class FlightController {
	//**
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private FlightService flightservice;
	
	/*@RequestMapping(value="/getflights",method=RequestMethod.GET)
	public List<Flights> getAll(){ //find all the flights in db
		return flightRepository.findAll();
	}*/
	
	/*@RequestMapping(value="/getflights",method=RequestMethod.GET)
	@ResponseBody
	  public ResponseEntity<List<Flights>> getAllFlight() {
		
		log.warn("Please provide the correct url");
		log.info("All Flight will be displayed in browser");
		
		try {
			List<Flights> flights = flightRepository.findAll();
			return new ResponseEntity<List<Flights>>(flights, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new FlightNotFoundException("Flight list already displayed in browser");
		}
		
	  }
	
	/*@RequestMapping(value="/addflights",method=RequestMethod.POST)
	public void addFlight(@RequestBody Flights flights) { //addflights onto db
		flightRepository.insert(flights);
	}*/
	
	/*@RequestMapping(value="/addflights",method=RequestMethod.POST) 
	 public ResponseEntity<Flights> addFlight(@RequestBody Flights flights) throws FlightAlreadyExistsException{
		
		log.info("Adding new flight to database " + flights.getFlight_id() + " " + " " + flights.getFlight_name() 
		+ " " + flights.getFrom_city() + " " + flights.getTo_city() + " " + flights.getDate_depart() + " " 
		+ flights.getTime_arrival() + " " + flights.getTime_depart() + " " + flights.getSeats() + " " + flights.getFare());
		
		if(flightRepository.existsById(flights.getFlight_id())) {
			throw new FlightAlreadyExistsException();
		}
		flightRepository.insert(flights);
		return new ResponseEntity("New Flight added successfully", HttpStatus.OK);
	  }
	
	//search flights based on the criteria
	@RequestMapping(value="/flights/{from_city}/{to_city}/{date_depart}",method=RequestMethod.GET)
	public List<Flights> getFlight(@PathVariable(value="from_city")String from_city,
			@PathVariable(value="to_city")String to_city,@PathVariable(value="date_depart")String date_depart){
		
		log.info("Flight by "+ from_city + " " + to_city + " " + date_depart);
		
		if(flightRepository.findByLocation(from_city, to_city, date_depart).isEmpty()){ 
			throw new FlightNotFoundException();
		}else {
		return flightRepository.findByLocation(from_city, to_city, date_depart);
		}
	}
	
	//used custom exception
	@RequestMapping(value="/flights/{flight_id}",method=RequestMethod.GET)
	@ResponseBody
	public Optional<Flights> getFlightById(@PathVariable int flight_id)throws  FlightNotFoundException{
		
		log.info("flight request by id "+ flight_id);
		Optional<Flights> flights;
		if(flightRepository.findById(flight_id).isEmpty()) {
			throw new FlightNotFoundException();
		}else {
			flights=flightRepository.findById(flight_id);
		}
		return flights;
	}*/
	
	/*@RequestMapping(value="/delflights/{flight_id}",method=RequestMethod.DELETE)
	public String deleteFlight (@PathVariable int flight_id) {
		  flightRepository.deleteById(flight_id);
			return "Order deleted with id : "+flight_id;
			}*/
	
	/*@RequestMapping(value="/delflights/{flight_id}",method=RequestMethod.DELETE)
	 public ResponseEntity<?> deleteFlight(@PathVariable int flight_id) {
		
		log.info("flight deleted with id "+ flight_id);
		
		if(flightRepository.findById(flight_id).isEmpty() ) {
			throw new FlightNotFoundException();
		}
		flightRepository.deleteById(flight_id);
		return new ResponseEntity<>("flight deleted successfully",HttpStatus.ACCEPTED);
	  }
	
	@RequestMapping(value="/updateflights/{flight_id}",method=RequestMethod.PUT)
	public String updateFlight (@PathVariable int flight_id,@RequestBody Flights flights) {
		
		log.info("flight updated with id "+ flight_id);
		
		  flights.setFlight_id(flight_id);
		  flightRepository.save(flights);
			return "Order updated with id : "+flight_id;
			}*/
	
	
	/*@ExceptionHandler(value =  FlightNotFoundException.class)
    public ResponseEntity<String>  FlightNotFoundException( FlightNotFoundException flightNotFoundException) {
        return new ResponseEntity<String>("Flight not found", HttpStatus.CONFLICT);
    }*/
	
	//}

package com.casestudy.searchservice.recource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.HttpServerErrorException;

import com.casestudy.searchservice.exception.FlightAlreadyExistsException;
import com.casestudy.searchservice.exception.FlightNotFoundException;
import com.casestudy.searchservice.models.Flights;
import com.casestudy.searchservice.repository.FlightRepository;
import com.casestudy.searchservice.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/searchflights")
public class FlightController {
	//**
	
	@Autowired
	private FlightService flightService;
	
	int count = 0;
	@RequestMapping("/demoflg")
	@Retryable(value = HttpStatusCodeException.class, maxAttempts=3, backoff = @Backoff(3000), exclude =
    HttpClientErrorException.class)
	public String CheckFlight() {
		int maxAttempts = 3;
		// code using loop
		while(count<maxAttempts) {
		   count++;
		   System.out.println("calling another service to get status!!");
		   throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return "application is up on port 8081" ;
	}
	
	@RequestMapping("/flights")
	@ResponseBody
	public ResponseEntity<List<Flights>> getAllFlight() throws FlightNotFoundException{
		
		return new ResponseEntity<List<Flights>>((List<Flights>)flightService.getAllFlight() ,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/flights/{from_city}/{to_city}/{date_depart}",method=RequestMethod.GET)
	public ResponseEntity<List<Flights>> getFlight(@PathVariable(value="from_city")String from_city,
			@PathVariable(value="to_city")String to_city,@PathVariable(value="date_depart")String date_depart) 
	throws FlightNotFoundException{
		
		return new ResponseEntity(flightService.getFlight(from_city, to_city, date_depart), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addflights",method=RequestMethod.POST) 
	 public ResponseEntity<Flights> addFlight(@RequestBody Flights flights) throws FlightAlreadyExistsException{
		
		return new ResponseEntity(flightService.addFlight(flights), HttpStatus.OK);
	  }
	
	@RequestMapping(value="/updateflights/{flight_id}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateFlight (@PathVariable int flight_id,@RequestBody Flights flights) {
		
		return new ResponseEntity<>(flightService.updateFlight(flight_id, flights),HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/delflights/{flight_id}",method=RequestMethod.DELETE)
	 public ResponseEntity<?> deleteFlight(@PathVariable int flight_id) throws FlightNotFoundException{
		
		return new ResponseEntity<>(flightService.deleteFlight(flight_id),HttpStatus.ACCEPTED);
	  }
}