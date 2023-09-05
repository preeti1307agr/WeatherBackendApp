# Use the official OpenJDK base image for Java
FROM openjdk:8

# Copy the Spring Boot application JAR into the container
ADD target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

# Expose the port the application will run on
EXPOSE 8000

# Define the command to run the application when the container starts
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
