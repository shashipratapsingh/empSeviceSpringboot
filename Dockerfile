# Use a base image with JDK 17 or whichever version you are using
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/EmployeeService-0.0.1-SNAPSHOT.jar /app/EmployeeService-0.0.1-SNAPSHOT.jar

# Expose the application port (adjust the port based on your application configuration)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "EmployeeService-0.0.1-SNAPSHOT.jar"]
