package com.ocbc.poc.notification.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@TestPropertySource("/application-dev.properties")
class EmailServiceTests {
	
	@Autowired
	private EmailService emailService;

	@Test
	void testSendMail() {
		
		emailService.sendEmail("test@gmail.com", 
								"Testing Email", 
								"Hi there");
	}

}
