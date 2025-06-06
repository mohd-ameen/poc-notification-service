package com.ocbc.poc.notification.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String recipient;
	private NotificationType type;
	private String content;
}
