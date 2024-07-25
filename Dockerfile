FROM eclipse-temurin:21
#FROM openjdk:18
MAINTAINER myEmail@gmail.com

# Set up work directory
ENV JAR_NAME=SpringBoot-reference-1.0.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME
COPY . .

# Copy the jar file into our app
#COPY --from=$APP_HOME .

# Exposing port as per app yml/property
EXPOSE 8090

# Starting the application
CMD ["java", "-jar", "SpringBoot-reference-1.0.jar"]