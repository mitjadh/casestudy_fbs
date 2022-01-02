package com.casestudy.bookingservice.resource;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.bookingservice.config.MessagingConfig;
import com.casestudy.bookingservice.models.Booking;
import com.casestudy.bookingservice.models.BookingStatus;

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
	
    @Autowired
    private RabbitTemplate template;
	
	@RequestMapping(value="/booking",method=RequestMethod.POST)
	//@HystrixCommand(fallbackMethod= "fallback_post" )
	public Booking addFlight(@RequestBody Booking booking ) {
		booking.setId(sequenceGeneratorService.generateSequence(Booking.SEQUENCE_NAME));
		BookingStatus orderStatus = new BookingStatus(booking, "PROCESS", "booking done succesfully");
	    template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
	    return bookingRepository.save(booking);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public List<Booking> getFlight(@PathVariable long id){
		return bookingRepository.findById(id);
	}
	
	/*@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	//@HystrixCommand(fallbackMethod= "fallback_put" )
	public Booking updateOrder(@PathVariable("id") long id,@RequestBody Booking booking) {
		booking.setId(id);
		bookingRepository.save(booking);
		return booking;
	}*/
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	//@HystrixCommand(fallbackMethod= "fallback_put" )
	public ResponseEntity<?> updateOrder(@PathVariable("id") long id,@RequestBody Booking booking) {
		if(bookingRepository.existsById(booking.getId())) {
		booking.setId(id);
		//return new ResponseEntity<>(bookingRepository.save(booking),HttpStatus.ACCEPTED);
		
		//return new ResponseEntity<>("flight updated",HttpStatus.ACCEPTED);
	}
		return new ResponseEntity<>(bookingRepository.save(booking),HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	//@HystrixCommand(fallbackMethod= "fallback_booking" )
	 public String deleteFlight (@PathVariable long id) {
	  bookingRepository.deleteById(id);
		return "Order deleted with id : "+id;
		}
	
	//pagination
	@RequestMapping(value = "/listPageable", method = RequestMethod.GET)
	Page<Booking> checkinGet(Pageable pageable) {
		return bookingRepository.findAll(pageable);

	}
	
	//pagination sorting
	@RequestMapping(value= "/pagingAndShortingPassengers/{pageNumber}/{pageSize}",
            method = RequestMethod.GET)
    public Page<Booking> passengerPagination(@PathVariable Integer pageNumber,
                                             @PathVariable Integer pageSize)
                                              {
        return sequenceGeneratorService.getPassengerPagination(pageNumber, pageSize);
    }
	
	/*public Booking fallback_post(Booking booking) {
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
		
	}*/
	
	/*public ResponseEntity<?> fallback_put(long id,Booking booking){
		return new ResponseEntity<>("Id not Found", HttpStatus.CONFLICT);
		
	}*/
	
	public String fallback_booking(long id) {
		return "Id not found";
	}
}