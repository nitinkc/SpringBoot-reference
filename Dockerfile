FROM eclipse-temurin:17-jdk-alpine
#FROM openjdk:18
MAINTAINER suchismitadeb2000@gmail.com

# Set up work directory
WORKDIR /app

# Copy the jar file into our app
COPY build/libs/springboot-basics-1.0.jar /app

# Exposing port as per app yml/property
EXPOSE 8090

# Starting the application
CMD ["java", "-jar", "springboot-basics-1.0.jar"]