package com.casestudy.checkinservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.casestudy.checkinservice.dto.CheckInDto;

public interface CheckInService {
	ResponseEntity<CheckInDto> addReferenceId(CheckInDto checkin);
	CheckInDto getFlightDetail(long _id);
	ResponseEntity<?> deleteFlight(@PathVariable long _id);
	CheckInDto updateFlight(long _id, CheckInDto checkin);
	public List<CheckInDto> getAllPassengers();
		
}
