package com.casestudy.bookingservice;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.casestudy.bookingservice.models.Booking;
import com.casestudy.bookingservice.repository.BookingRepository;
import com.casestudy.bookingservice.resource.BookingController;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookingServiceApplicationTests {

	@Autowired
	private BookingController bookingController;
	
	@MockBean
	private BookingRepository bookingrepo;
	
	/*@Test
	public void getFlightTest() {
		String id="6S33";
		when(bookingrepo.findById(id)).
		thenReturn(Stream.of(new Booking("6S33","30C",2,"Mumbai","Delhi"))
				.collect(Collectors.toList()));
		assertEquals(1, bookingController.getFlight(id).size());
	}
	
	@Test
	public void addFlightTest() {
		Booking booking = new Booking("6S33","30C",2,"Mumbai","Delhi");
		when(bookingrepo.insert(booking)).thenReturn(booking);
		assertEquals(booking, bookingController.addFlight(booking));
	}*/
	
	

}
