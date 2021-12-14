package com.casestudy.checkinservice.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.casestudy.checkinservice.exception.FlightNotFoundException;
import com.casestudy.checkinservice.models.CheckIn;
import com.casestudy.checkinservice.repository.CheckInRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/checkin")
public class CheckInController {
	
	@Autowired
	private CheckInRepository checkinRepository;
	
	//find id from booking db
	@RequestMapping(value="/{_id}",method=RequestMethod.GET)
	@Cacheable(value="checkin", key="#_id")
	public Optional<CheckIn> getFlight(@PathVariable long _id){
		log.info("fetching from db");
		return checkinRepository.findById(_id);
		
	}
	
	//delete id from booking db
	@RequestMapping(value="/del/{_id}",method=RequestMethod.DELETE)
	@CacheEvict(value="checkin",key="#_id")
	public String deleteFlight(@PathVariable long _id){
		checkinRepository.deleteById(_id);
		return "Flight deleted with id : "+_id;
		
	}
	
	@RequestMapping(value="/update/{_id}",method=RequestMethod.PUT)
	@CachePut(value="checkin",key="#_id")
	public CheckIn updateFlight(@PathVariable("_id") long _id,@RequestBody CheckIn checkin ) {
		//start time
		checkinRepository.save(checkin);
		log.info("Flight updated successfully");
		//end time
		return checkin;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void addFlight(@RequestBody CheckIn checkin) {
		checkinRepository.insert(checkin);
	}
	
    @GetMapping("/getFlightDetails/{_id}")
    public Optional<CheckIn> getTeacherDetails(@PathVariable("_id") long _id){
        Optional<CheckIn> checkin = checkinRepository.findById(_id);
        if(!checkin.isPresent())
            throw new FlightNotFoundException();
        
        return checkin;
    }

}
