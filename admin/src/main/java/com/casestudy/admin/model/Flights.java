package com.casestudy.admin.model;


import java.util.List;
import java.util.stream.Collector;

import org.springframework.format.annotation.DateTimeFormat;



public class Flights {
	
	private int flight_id;
	private String flight_name;
	//private String date_depart;
	private String date_depart;
	private String from_city;
	private String to_city;
	private String time_depart;
	private String time_arrival;
	private int seats;
	private int fare;
	//private String seat_start;
	
	/*public Flights(int flight_id, String flight_name, String date_depart, String from_city, String to_city,
			String time_depart, String time_arrival, int seats, int fare, String seat_start) {
		super();*/
	public Flights(int flight_id, String flight_name, String date_depart, String from_city, String to_city,
			String time_depart, String time_arrival, int seats, int fare) {
		super();
		this.flight_id = flight_id;
		this.flight_name = flight_name;
		this.date_depart = date_depart;
		this.from_city = from_city;
		this.to_city = to_city;
		this.time_depart = time_depart;
		this.time_arrival = time_arrival;
		this.seats = seats;
		this.fare = fare;
		//this.seat_start = seat_start;
	}
	
	public int getFlight_id() {
		return flight_id;
	}
	
	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}
	
	public String getFlight_name() {
		return flight_name;
	}
	
	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
	}
	
	public String getDate_depart() {
		return date_depart;
	}
	
	public void setDate_depart(String date_depart) {
		this.date_depart = date_depart;
	}
	
	public String getFrom_city() {
		return from_city;
	}
	
	public void setFrom_city(String from_city) {
		this.from_city = from_city;
	}
	
	public String getTo_city() {
		return to_city;
	}
	
	public void setTo_city(String to_city) {
		this.to_city = to_city;
	}
	
	public String getTime_depart() {
		return time_depart;
	}
	
	public void setTime_depart(String time_depart) {
		this.time_depart = time_depart;
	}
	
	public String getTime_arrival() {
		return time_arrival;
	}
	
	public void setTime_arrival(String time_arrival) {
		this.time_arrival = time_arrival;
	}
	
	public int getSeats() {
		return seats;
	}
	
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public int getFare() {
		return fare;
	}
	
	public void setFare(int fare) {
		this.fare = fare;
	}
	
	/*public String getSeat_start() {
		return seat_start;
	}
	
	public void setSeat_start(String seat_start) {
		this.seat_start = seat_start;
	}*/
	
	@Override
	public String toString() {
		return "Flights [flight_id=" + flight_id + ", flight_name=" + flight_name + ", date_depart=" + date_depart
				+ ", from_city=" + from_city + ", to_city=" + to_city + ", time_depart=" + time_depart
				+ ", time_arrival=" + time_arrival + ", seats=" + seats + ", fare=" + fare + "]";
	}
}
