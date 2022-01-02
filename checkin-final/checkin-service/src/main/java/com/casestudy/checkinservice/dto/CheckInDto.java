package com.casestudy.checkinservice.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class CheckInDto {
	
	@Id
	private long _id;
	private String seat_type;
	public String seat_no;
	public String quantity;
	public String first_name;
	public String last_name;
	public int flight_id;

}
