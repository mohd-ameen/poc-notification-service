package com.ocbc.poc.notification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ocbc.poc.notification.entity.Notification;
import com.ocbc.poc.notification.exception.ResourceNotFoundException;
import com.ocbc.poc.notification.repository.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	public List<Notification> getAllNotifications() {
		return notificationRepository.findAll();
	}
	
	@Transactional
	public Notification createNotification(Notification notification) {
		 return notificationRepository.save(notification);
	}
	
	@Cacheable(value = "Notification", key = "#id") //unless = "#Notification.NotificationType=EMAIL"
	public Notification getNotificationById(Long id) {
		return notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Record not found for Notification Id: "+id));
	}
	
	@Transactional
	@CachePut(value = "Notification", key = "#id")
	public Notification updateNotification(Long id, Notification notification) {
		Notification existingNotification = notificationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found for Notification Id: "+id));

		existingNotification.setRecipient(notification.getRecipient());
		existingNotification.setType(notification.getType());
		existingNotification.setContent(notification.getContent());

		return notificationRepository.save(existingNotification);
	}
	
	@Transactional
	@CacheEvict(value = "Notification", key = "#id")
	public void deleteNotification(Long id) {
		Notification notification = notificationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found for Notification Id: "+id));
		notificationRepository.delete(notification);
	}
}
