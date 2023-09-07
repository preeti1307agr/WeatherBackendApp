# Use the official OpenJDK base image for Java
FROM openjdk:8

# Copy the Spring Boot application JAR into the container
COPY ./target/*.jar weatherapp.jar

# Expose the port the application will run on
EXPOSE 7000

# Define the command to run the application when the container starts
CMD ["java", "-jar", "weatherapp.jar"]
