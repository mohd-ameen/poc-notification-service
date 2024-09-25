package com.ocbc.poc.notification.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.ocbc.poc.notification.bo.NotificationRequest;
import com.ocbc.poc.notification.bo.NotificationType;
import com.ocbc.poc.notification.constants.KafkaConstants;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = KafkaConstants.NOTIFICATION_TOPIC, brokerProperties = {
		"listeners=PLAINTEXT://localhost:9092", "port=9092"
})
@TestPropertySource("/application-junittest.properties")
@ActiveProfiles("junittest")
@Slf4j
public class NotificationControllerTest {

	@Autowired
	private NotificationController controller;
	
	@Test
	public void testPublishNotification() {
		
		NotificationRequest request = NotificationRequest.builder()
										.id("1")
										.recipient("ameen@gmail.com")
										.type(NotificationType.EMAIL)
										.content("Sending email Notification")
										.build();
		
		controller.publishNotification(request);
		
	}
}
