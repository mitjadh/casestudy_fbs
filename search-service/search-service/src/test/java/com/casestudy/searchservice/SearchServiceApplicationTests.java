package com.casestudy.searchservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.casestudy.searchservice.models.Flights;
import com.casestudy.searchservice.recource.FlightController;
import com.casestudy.searchservice.repository.FlightRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class SearchServiceApplicationTests {

	@Autowired
	private FlightController flightController;
	
	@MockBean
	private FlightRepository flightRepository;
	
	/*@Test
	public void getAllTest() {
		when(flightRepository.findAll()).thenReturn(Stream
				.of(new Flights(10,"Air India","20-09-2022","Mumbai","Delhi","12:20 am","1:30 pm",40,7000,"A1"), 
						new Flights(11,"Indian Airlines","23-10-2022","Mumbai","Kerala","1:00 pm","2:30 pm",60,8000,"B1"))
				.collect(Collectors.toList()));
		assertEquals(2, flightController.getAllFlight().size());
	}

	@Test
	public void getFlightTest() {
		String from_city = "Pune";
		String to_city = "Nagaland";
		String date_depart = "30-09-2021";
		when(flightRepository.findByLocation(from_city, to_city, date_depart))
		.thenReturn(Stream.of(new Flights(10,"Air India","20-09-2022","Mumbai","Delhi","12:20 am","1:30 pm",40,7000,"A1"))
				.collect(Collectors.toList()));
		assertEquals(1, flightController.getFlight(from_city, to_city, date_depart).size());
	}
	
	/*@Test
	public void addFlightTest() {
		Flights flights = new Flights(10,"Air India","20-09-2022","Mumbai","Delhi","12:20 am","1:30 pm",40,7000);
		when(flightRepository.save(flights)).thenReturn(flights);
		assertEquals(flights, flights.addFlight(flights));*/
	}