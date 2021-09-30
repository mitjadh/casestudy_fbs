package com.casestudy.checkinservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="TicketBooking")
public class CheckIn {
	
	@Id
	private long _id;
	private String seat_type;
	public String seat_no;
	public String quantity;
	public String first_name;
	public String last_name;
	public int flight_id;
	
	public CheckIn() {
		
	}

	public CheckIn(long _id, String seat_type, String seat_no, String quantity, String first_name, String last_name,
			int flight_id) {
		super();
		this._id = _id;
		this.seat_type = seat_type;
		this.seat_no = seat_no;
		this.quantity = quantity;
		this.first_name = first_name;
		this.last_name = last_name;
		this.flight_id = flight_id;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getSeat_type() {
		return seat_type;
	}

	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	@Override
	public String toString() {
		return "CheckIn [_id=" + _id + ", seat_type=" + seat_type + ", seat_no=" + seat_no + ", quantity=" + quantity
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", flight_id=" + flight_id + "]";
	}
	
}
