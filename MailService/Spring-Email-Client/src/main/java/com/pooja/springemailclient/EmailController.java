/*package com.pooja.springemailclient;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class EmailController {
	
	@Autowired
	private EmailSenderService service;
	
	@EventListener(ApplicationReadyEvent.class)
	@RequestMapping(method=RequestMethod.POST, value="/sendemail")
	public void triggerMail(@PathVariable String toEmail, @PathVariable String body, @PathVariable String subject) throws MessagingException {

		service.sendSimpleEmail(toEmail, body, subject);
	}

}
*/