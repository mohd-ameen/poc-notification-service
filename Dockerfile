# OpenJDK 17 image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy our JAR file to our working directory
COPY poc-notification-service-0.0.1-SNAPSHOT.jar /app/poc-notification-service.jar

EXPOSE 8080

# Set the command to run the JAR file
ENTRYPOINT [ "java", "-jar", "poc-notification-service.jar" ]