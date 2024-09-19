package com.ocbc.poc.notification.repository;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.ocbc.poc.notification.bo.NotificationType;
import com.ocbc.poc.notification.entity.Notification;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@TestPropertySource("/application-dev.properties")
public class NotificationRepositoryTest {

	@Autowired
	private NotificationRepository repository;
	
	@Test
	public void testAddNotification() {
		
		Notification notification = Notification.builder()
									.recipient("ameen@gmail.com")
									.type(NotificationType.EMAIL)
									.content("Email Notification")
									.build();
		
		
		repository.save(notification);
	}
	
	@Test
	public void testAllNotification() {
		
		List<Notification> notificationList = repository.findAll();
		log.info("NotificationLists: {}", notificationList);
	}
	
}
