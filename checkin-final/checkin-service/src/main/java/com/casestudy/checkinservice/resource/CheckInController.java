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
import org.springframework.ui.Model;
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

import com.casestudy.checkinservice.dto.CheckInDto;
import com.casestudy.checkinservice.exception.FlightNotFoundException;
import com.casestudy.checkinservice.models.CheckIn;
import com.casestudy.checkinservice.repository.CheckInRepository;
import com.casestudy.checkinservice.service.CheckInService;

import lombok.extern.slf4j.Slf4j;

/*@RestController
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
		
	}*/
	
	//delete id from booking db
	//changes
	/*@RequestMapping(value="/del/{_id}",method=RequestMethod.DELETE)
	@Retryable(value = HttpStatusCodeException.class, maxAttempts = 3, backoff = @Backoff(3000), exclude =
    HttpClientErrorException.class)
	@CacheEvict(value="checkin",key="#_id")
	public String deleteFlight(@PathVariable long _id){
		
		//log.info("Flight deleted with id : "+_id);
		
		//checkinRepository.deleteById(_id);
		System.out.println("calling other service to get status");
		throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		//return "Flight deleted with id : "+_id;
		
	}
	
	@Recover
	public String recover(HttpServerErrorException exception) {
		return "Please try after some time";
	}*/
	
	/*@RequestMapping(value="/del/{_id}",method=RequestMethod.DELETE)
	@Retryable(value = HttpStatusCodeException.class, maxAttempts = 3, backoff = @Backoff(3000), exclude =
    HttpClientErrorException.class)
	@CacheEvict(value="checkin",key="#_id")
	public ResponseEntity<?> deleteFlight(@PathVariable long _id){
		
		//log.info("Flight deleted with id : "+_id);
		if(checkinRepository.findById(_id).isPresent()) {
			checkinRepository.deleteById(_id);
			return new ResponseEntity<>("flight has been deleted",HttpStatus.ACCEPTED);
		}
		
		System.out.println("Calling other service");
		throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		//System.out.println("calling other service to get status");
		//throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		//return "Flight deleted with id : "+_id;
		
	}
	
	@Recover
	public ResponseEntity<?> recover(HttpServerErrorException exception) {
		return new ResponseEntity<>("id not found",HttpStatus.CONFLICT);
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
	
	//second method of custom exceptions
    @GetMapping("/getFlightDetails/{_id}")
    public Optional<CheckIn> getTeacherDetails(@PathVariable("_id") long _id){
        Optional<CheckIn> checkin = checkinRepository.findById(_id);
        if(checkin.isEmpty())
            throw new FlightNotFoundException();
        
        return checkin;
    }
    }*/
    
    //pagination
    /*@RequestMapping(value = "/listPageable", method = RequestMethod.GET)
	Page<CheckIn> checkinGet(Pageable pageable) {
		return checkinRepository.findAll(pageable);

	}*/

@RestController
@Slf4j
//@CrossOrigin(origins = "http://localhost:3000")
public class CheckInController {

	@Autowired
	private CheckInService checkInService;
	
	//@Transactional
	@RequestMapping(method=RequestMethod.POST, value="/checkin")
	public ResponseEntity<CheckInDto> addReferenceId(@RequestBody CheckInDto checkin) {
		
		
		return new ResponseEntity(checkInService.addReferenceId(checkin),HttpStatus.ACCEPTED);
	}
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="/checkin/{_id}")
	//@Cacheable(value="checkin", key="#_id")
	public ResponseEntity<CheckInDto> getFlightDetail(@PathVariable long _id) {
		
		
		return new ResponseEntity(checkInService.getFlightDetail(_id),HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/checkin/{_id}")
	
	@CacheEvict(value="checkin", key="#_id")
	public ResponseEntity<?> deleteFlight(@PathVariable long _id) {
        return new ResponseEntity<>(checkInService.deleteFlight(_id),HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/checkin/{_id}")
	//@CachePut(value="checkin", key="#_id")
	public ResponseEntity<?> updateFlight(@PathVariable long _id, @RequestBody CheckInDto checkin) {
	
		 return new ResponseEntity<>(checkInService.updateFlight(_id, checkin),HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping("/checkin")
	public ResponseEntity<?> getAllPassengers() {
		List<CheckInDto> chk = checkInService.getAllPassengers();
		return ResponseEntity.ok(chk);
	}
	
	
}
