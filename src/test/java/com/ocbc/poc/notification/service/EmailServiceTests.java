package com.ocbc.poc.notification.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ActiveProfiles("junittest")
@TestPropertySource("/application-junittest.properties")
class EmailServiceTests {
	
	@MockBean
	private EmailService emailService;

	@Test
	void testSendMail() {
		
		emailService.sendEmail("ameen@gmail.com", 
								"Testing Email", 
								"Hi there");
	}

}
