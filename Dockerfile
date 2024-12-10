# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
COPY . .  # Copy the source code into the /app directory inside the container
RUN mvn clean package -DskipTests  # Build the Spring Boot application

# Stage 2: Create a lightweight runtime image
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/server-0.0.1-SNAPSHOT.jar server.jar  # Copy the jar from the build stage to the runtime image
EXPOSE 8080 
ENTRYPOINT ["java", "-jar", "server.jar"]  # Command to run the Spring Boot application
