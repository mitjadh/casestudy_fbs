package com.casestudy.checkinservice.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.checkinservice.models.CheckIn;
import com.casestudy.checkinservice.repository.CheckInRepository;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/checkin")
public class CheckInController {
	
	@Autowired
	private CheckInRepository checkinRepository;
	
	@RequestMapping(value="/checkindetails/{_id}",method=RequestMethod.GET)
	public Optional<CheckIn> getFlight(@PathVariable long _id){
		return checkinRepository.findById(_id);
		
	}
	
	@RequestMapping(value="/checkindetails/del/{_id}",method=RequestMethod.DELETE)
	public String deleteFlight(@PathVariable long _id){
		checkinRepository.deleteById(_id);
		return "Flight deleted with id : "+_id;
		
	}
	

}
