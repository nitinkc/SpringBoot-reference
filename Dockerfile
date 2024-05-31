FROM eclipse-temurin:17-jdk-alpine
#FROM openjdk:18
MAINTAINER myEmail@gmail.com

# Set up work directory
WORKDIR /usr/app
COPY . .

ENV JAR_NAME=springboot-ref-1.0.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Copy the jar file into our app
COPY --from=$APP_HOME .

# Exposing port as per app yml/property
EXPOSE 8090

# Starting the application
CMD ["java", "-jar", "springboot-basics-1.0.jar"]