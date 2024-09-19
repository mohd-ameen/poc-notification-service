package com.ocbc.poc.notification.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.poc.notification.bo.NotificationRequest;
import com.ocbc.poc.notification.constants.KafkaConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationConsumer {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@KafkaListener(topics = KafkaConstants.NOTIFICATION_TOPIC, groupId = KafkaConstants.GROUP_ID)
	public void consumeNotification(String message) {

		log.info("Notification message: {}", message);
		
		try {
			NotificationRequest notification = mapper.readValue(message, NotificationRequest.class);
			
			// Store the notification content in Redis cache
			redisTemplate.opsForValue().set(notification.getId(), notification.getContent());
			
		} catch (Exception e) {
			log.error("Exception occurred: {}", e.getMessage());
		} 
		
	}

}
