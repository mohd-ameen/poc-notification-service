package com.ocbc.poc.notification.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.ocbc.poc.notification.constants.KafkaConstants;

@Configuration
public class KafkaConfig {

	@Bean
	public NewTopic topic() {
		
		return TopicBuilder
				.name(KafkaConstants.NOTIFICATION_TOPIC)
				.build();
	}
}
