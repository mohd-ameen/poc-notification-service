package com.ocbc.poc.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author Mohammad Ameen
 */
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@OpenAPIDefinition(info = @Info(title = "Notification Service APIs", 
				   version = "1.0", 
				   description = "A notification system where messages are sent to Kafka processed "
				   				+ "to determine the type of notification(Email, SMS, Push) "
				   				+ "and cached in Redis for quick access", 
				   contact = @Contact(name = "@meen", email = "demo@gmail.com"), 
				   license = @License(name = "Authorised Internal Use Only")),
				   servers = {
						   @Server(
								   description = "Dev",
								   url = "http://localhost:8080"
								   ),
						   @Server(
								   description = "Test",
								   url = "https://poc-notification-service-3-mameen1-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"
								   )
				   }
					
		)
public class PocNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocNotificationServiceApplication.class, args);
	}

}
