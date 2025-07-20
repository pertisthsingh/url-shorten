# Use a base image with Java 21
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the built jar file into the container
COPY target/*.jar app.jar

# Expose the port your app runs on (usually 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
