package com.pooja.springemailclient;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringEmailClientApplication {

	
	@Autowired
	private EmailSenderService service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringEmailClientApplication.class, args);
	}

	
	/*@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {

		service.sendSimpleEmail("sonaljadhaw68@gmail.com",
				"This is Email Body ",
				"This email is from Sonal"
				);
	}*/
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMain() throws MessagingException {

		service.sendMailWithAttachment("mitalijadhaw@gmail.com",
				"This is email body",
				//"This is email subject","/Users/arjungautam/Downloads/resume.pdf");
				"This is email subject","/Users/mjadhaw/Downloads/tree.jpg");
	}
		
}
