package com.ocbc.poc.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocbc.poc.notification.bo.NotificationRequest;
import com.ocbc.poc.notification.entity.Notification;
import com.ocbc.poc.notification.producer.NotificationProducer;
import com.ocbc.poc.notification.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/notification")
@Tag(name = "Notification APIs", description = "Notification Service Endpoints")
public class NotificationController {

	@Autowired
	private NotificationProducer notificationProducer;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private NotificationService notificationService;

	@Operation(operationId = "publishNotification", 
			   summary = "Publish NotificationRequest to Kafka", 
			   description = "Endpoint to send the messages to Kafka",
			   responses = {
					   @ApiResponse(
							 description = "Success",
							 responseCode = "200"
					   )
			     }
			   )

	@PostMapping("/publish")
	public ResponseEntity<String> publishNotification(@RequestBody NotificationRequest request) {

		notificationProducer.sendNotification(request);

		return ResponseEntity.ok("Notification sent to Kafka!");
	}

	@Operation(operationId = "getCachedMessage", 
			   summary = "Get value from Redis Cache", 
			   description = "Endpoint to get a cached message from Redis",
			   responses = {
					   @ApiResponse(
							 description = "Success",
							 responseCode = "200"
					   )
			     }
			   )

	@GetMapping("/get/{id}")
	public ResponseEntity<String> getCachedMessage(@PathVariable String id) {

		String cachedMessage = redisTemplate.opsForValue().get(id);

		if (cachedMessage != null) {
			return ResponseEntity.ok("Cached Value: " + cachedMessage);
		}
		return ResponseEntity.ok("No cached data found for key: " + id);
	}
	
	@Operation(operationId = "saveNotification", 
			   summary = "Persist Notification object", 
			   description = "Endpoint to Save the Notification object",
			   responses = {
					   @ApiResponse(
							 description = "Success",
							 responseCode = "201"
					   )
			     }
			   )
	@PostMapping("/save")
	public ResponseEntity<?> saveNotification(@RequestBody Notification request) {

		notificationService.createNotification(request);

		return new ResponseEntity<>("Record Saved Successfully!", HttpStatus.CREATED);
	}
	
	@Operation(operationId = "getAllNotifications", 
			   summary = "Get all Notifications from DB", 
			   description = "Endpoint to get all the Notifications from DB.",
			   responses = {
					   @ApiResponse(
							 description = "Success",
							 responseCode = "200"
					   ),
					   @ApiResponse(
								 description = "Record Not Found",
								 responseCode = "404"
						   )
			     }
			   )
	@GetMapping("/all")
	public ResponseEntity<List<Notification>> getAllNotifications() {

		List<Notification> notifications = notificationService.getAllNotifications();

		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}
	
	@Operation(operationId = "getNotificationById", 
			   summary = "Get a Notifications By Id", 
			   description = "Endpoint to get a Notification from DB.",
			   responses = {
					   @ApiResponse(
							 description = "Success",
							 responseCode = "200"
					   ),
					   @ApiResponse(
								 description = "Record Not Found",
								 responseCode = "404"
						   )
			     }
			   )
	@GetMapping("/{id}")
	public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {

		Notification notification = notificationService.getNotificationById(id);
		return new ResponseEntity<>(notification, HttpStatus.OK);

	}
	
	@Operation(operationId = "updateNotification", 
			   summary = "Update the Notification", 
			   description = "Endpoint to update the Notification in DB.",
			   responses = {
					   @ApiResponse(
							 description = "Success",
							 responseCode = "200"
					   ),
					   @ApiResponse(
								 description = "Record Not Found",
								 responseCode = "404"
						   )
			     }
			   )
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateNotification(@PathVariable Long id, @RequestBody Notification request) {

		notificationService.updateNotification(id, request);
		return new ResponseEntity<>("Record Updated Successfully!", HttpStatus.OK);

	}
	
	@Operation(operationId = "deleteNotification", 
			   summary = "Delete the Notification", 
			   description = "Endpoint to delete a Notification in DB.",
			   responses = {
					   @ApiResponse(
							 description = "No Content",
							 responseCode = "204"
					   ),
					   @ApiResponse(
								 description = "Record Not Found",
								 responseCode = "404"
						   )
			     }
			   )
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {

		notificationService.deleteNotification(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
