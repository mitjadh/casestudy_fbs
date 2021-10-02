package com.pixeltrice.springbootOTPapp;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Component
	public class SmsService {

	    
	    private final String ACCOUNT_SID ="ACc18844d29ba72cfd28f81d06a67793ca";

	    private final String AUTH_TOKEN = "e850ba988af7e7b67f6d58faf3bbfa1e";

	    private final String FROM_NUMBER = "+12184034811";

	    public void send(SmsPojo sms) {
	    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
	                .create();
	        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

	    }

	    public void receive(MultiValueMap<String, String> smscallback) {
	    }
	
}
