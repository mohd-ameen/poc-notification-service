package com.ocbc.poc.notification.repository;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.ocbc.poc.notification.bo.NotificationType;
import com.ocbc.poc.notification.entity.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles("junittest")
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotificationRepositoryTest {

	@Autowired
	private NotificationRepository repository;
	
	@Test
	@DisplayName("Test 1:Save Notification Test")
	@Order(1)
	@RepeatedTest(5)
	public void testAddNotification() {
		
		Notification notification = Notification.builder()
									.recipient("ameen@gmail.com")
									.type(NotificationType.EMAIL)
									.content("Email Notification!")
									.build();
		
		repository.save(notification);
	}
	
	@Test
	@DisplayName("Test 2: Get All Notification Test")
	@Order(2)
	public void testGetAllNotification() {
		
		List<Notification> notificationList = repository.findAll();
		
		log.info("NotificationLists: {}", notificationList);
	}
	
}
