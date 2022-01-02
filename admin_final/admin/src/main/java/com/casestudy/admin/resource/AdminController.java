package com.casestudy.admin.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.casestudy.admin.model.Flights;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
	
	private static final String ADMIN ="admin" ;
	
	private int attempts=1;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//@RequestMapping(value="/addflg",method=RequestMethod.POST)
	
	@Retry(name = ADMIN,fallbackMethod = "fallback_retry")
	@RequestMapping(value = "/deletflg/{flight_id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delFlight(@PathVariable Integer flight_id) {
		log.info("Search Service call attempted: "+ attempts++);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Flights> httpentity=new HttpEntity<>(headers);
		restTemplate.delete("http://search-service/searchflights/delflights/"+flight_id,httpentity,Flights.class);
	    log.info("Search Service Called");
		return new ResponseEntity<>("Flight deleted successfully",HttpStatus.OK);
	}
	
	public ResponseEntity<String> fallback_retry(Exception e){
       attempts=1;
    return new ResponseEntity<String>("Search Service is Down, please try after some time :)", HttpStatus.INTERNAL_SERVER_ERROR);

}
	
	@GetMapping(value="/getflg")
	public List<Flights> getAllFlights() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<List> httpentity=new HttpEntity<>(headers);
		return restTemplate.getForObject("http://search-service/searchflights/getflights", List.class);
		
		
	}

	@PostMapping(value="/addflg")
	public void addFlight(@RequestBody Flights flights) {
		//flights = restTemplate.postForObject("http://search-service/", Flights.class, null, null);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Flights> httpentity=new HttpEntity<>(flights,headers);
		flights=restTemplate.postForObject("http://search-service/searchflights/addflights",httpentity,Flights.class);
		
	}
	
	@DeleteMapping(value="/delflg/{flight_id}")
	public void deleteFlight(@PathVariable int flight_id ) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Flights> httpentity=new HttpEntity<>(headers);
		restTemplate.delete("http://search-service/searchflights/delflights/"+flight_id,httpentity,Flights.class);
		
	}
	
	@PutMapping(value="/updateflg/{flight_id}")
	public void updateFlight(@PathVariable int flight_id,@RequestBody Flights flights) {
		//flights = restTemplate.postForObject("http://search-service/", Flights.class, null, null);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Flights> httpentity=new HttpEntity<>(flights,headers);
		restTemplate.put("http://search-service/searchflights/updateflights/"+flight_id,httpentity,Flights.class);
	}

}
