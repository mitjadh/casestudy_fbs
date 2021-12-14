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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/flightbooking")
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@RequestMapping(value="/booking",method=RequestMethod.POST)
	@HystrixCommand(fallbackMethod= "fallback_post" )
	public Booking addFlight(@RequestBody Booking booking ) {
		booking.setId(sequenceGeneratorService.generateSequence(Booking.SEQUENCE_NAME));
		return bookingRepository.save(booking);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public List<Booking> getFlight(@PathVariable long id){
		return bookingRepository.findById(id);
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	@HystrixCommand(fallbackMethod= "fallback_put" )
	public Booking updateOrder(@PathVariable("id") long id,@RequestBody Booking booking ) {
		bookingRepository.save(booking);
		return booking;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	@HystrixCommand(fallbackMethod= "fallback_booking" )
	 public String deleteFlight (@PathVariable long id) {
	  bookingRepository.deleteById(id);
		return "Order deleted with id : "+id;
		}
	
	public Booking fallback_post(Booking booking) {
		Booking book=new Booking();
		book.setFlight_id(0);
		book.setFirst_name("fallback_first");
		book.setLast_name("fallback_last");
		book.setId(0);
		book.setQuantity(0);
		book.setSeat_no("No seat selected");
		book.setSeat_type("No seat type selected");
		return book;
		
	}
	
	public Booking fallback_put(Booking booking,long id) {
		Booking book=new Booking();
		book.setFlight_id(0);
		book.setFirst_name("fallback_put_first");
		book.setLast_name("fallback_put_last");
		book.setId(0);
		book.setQuantity(0);
		book.setSeat_no("No seat selected");
		book.setSeat_type("No seat type selected");
		return book;
		
	}
	
	public String fallback_booking(long id) {
		return "Id not found";
	}
}