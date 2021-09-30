package com.casestudy.bookingservice.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.bookingservice.models.Booking;
import static com.casestudy.bookingservice.models.Booking.*;
import com.casestudy.bookingservice.repository.BookingRepository;
import com.casestudy.bookingservice.service.SequenceGeneratorService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/flightbooking")
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@RequestMapping(value="/booking",method=RequestMethod.POST)
	public Booking addFlight(@RequestBody Booking booking ) {
		booking.setId(sequenceGeneratorService.generateSequence(Booking.SEQUENCE_NAME));
		return bookingRepository.save(booking);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public List<Booking> getFlight(@PathVariable String id){
		return bookingRepository.findById(id);
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	public Booking updateOrder(@PathVariable("id") String id,@RequestBody Booking booking ) {
		bookingRepository.save(booking);
		return booking;
	}
		
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	 public String deleteFlight (@PathVariable String id) {
	  bookingRepository.deleteById(id);
		return "Order deleted with id : "+id;
		}
}

