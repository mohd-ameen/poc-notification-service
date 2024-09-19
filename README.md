## Real Time Notification System
A notification system where messages are sent to Kafka processed to determine the type of notification(Email, SMS, Push) and cached in Redis for quick access. The application is containerized using Docker & Deployed in K8s and Openshift.

## Technologies
- Java 17
- Maven 3+
- Spring Boot 3.3.3
- Kafka
- Redis
- MySQL 8.0
- Openshift
- Spring Tool Suite

### Preparing Environment

From the project's folder, execute:
- `docker-compose up` to initialize Kafka, Zookeeper, Redis and MySQL
- `mvn package` to build the applications

### Booting Applications
```bash
$ cd poc-notification-service
$ mvn spring-boot:run
```

### Kafka Set Up in Local environment
```bash
1. Start Zookeeper service: 
   C:\kafka>bin\windows\zookeeper-server-start.bat config\zookeeper.properties 

2. Start Kafka Server:
   C:\kafka>bin\windows\kafka-server-start.bat config\server.properties
   
3. Create Kafka Topic:
   C:\kafka>bin\windows\kafka-topics.bat --create --topic notification-topic --bootstrap-server localhost:9092

4. Produce message to Topic:
   C:\kafka>bin\windows\kafka-console-producer.bat --topic notification-topic --bootstrap-server localhost:9092
   
5. Consume messages from Topic:
   C:\kafka>bin\windows\kafka-console-consumer.bat --topic notification-topic --from-beginning --bootstrap-server localhost:9092

6. List all Topics:
   C:\kafka>bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
   
7. Describe a Topic:
   C:\kafka>bin\windows\kafka-topics.bat --describe --topic notification-topic --bootstrap-server localhost:9092
   
```

### Using Docker Images

* _bitnami/zookeeper_ <br>
  Set the following Environment variable: <br><br>
  **ALLOW_ANONYMOUS_LOGIN &emsp; yes**
  <br> <br>
* _bitnami/kafka_ <br>
  Set the following Environment variable: <br><br>
  **KAFKA_ZOOKEEPER_CONNECT &emsp;&emsp;&emsp; zookeeper:2181** <br>
  **ALLOW_PLAINTEXT_LISTENER &emsp;&emsp;&emsp;&emsp; yes** <br>
  **KAFKA_CFG_LISTENERS &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; PLAINTEXT://:9092** <br>
  **KAFKA_CFG_ADVERTISED_LISTENERS &emsp; PLAINTEXT://kafka:9092** <br>
  **KAFKA_ADVERTISED_PORT &emsp;&emsp;&emsp;&emsp;&emsp; 9092**
  <br> <br>
* _redis_ <br>
  Set the following Environment variable: <br><br>
  **REDIS_PASSWORD &emsp;&emsp; root**

### Profiles
- dev
- test

For each profile application-{env}.properties file is created.


### API Documentation
```bash
http://localhost:8080/swagger-ui.html
```

### Testing

- Kafka Publish:

```bash
curl --location 'http://localhost:8080/notification/publish' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "1",
    "recipient": "ameen@xyz.com",
    "type": "PUSH",
    "content": "This is a PUSH notification."
}'
````

- Get data from Redis:

```bash
curl --location 'http://localhost:8080/notification/get/207'
```

### Deployment in Openshift
1. Create a RedHat account and launch OpenShift Sanbox console.
2. Go to Add option in menu and import your application source code, there are multiple options
   by uploading JAR file OR from Git repo.
3. Add configuration details and click on create, it will start building the application.
4. In order to add Kafka, Redis and MySQL images, click on Add and go to Container images and add the        image details.
5. Test the application and monitor.
<br>

**NOTE:** Use the Cluster IP in properties file for Kafka, Redis & MySQL hostname.

 


