package com.ocbc.poc.notification.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.poc.notification.bo.NotificationRequest;
import com.ocbc.poc.notification.constants.KafkaConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationProducer {

	private static final ObjectMapper mapper = new ObjectMapper();
	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendNotification(NotificationRequest request) {
		kafkaTemplate.send(KafkaConstants.NOTIFICATION_TOPIC, toJsonString(request));
	}

	/**
	 * Convert Object to json
	 *
	 * @param object object
	 * @return string json
	 */
	private <T> String toJsonString(T object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("Exception occurred: {}", e.getMessage());
			return null;
		}
	}
}
