package com.spring.reference.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

@UtilityClass
public class ServerUtils {
    public static String getRequestPayload(HttpServletRequest request) throws IOException {
        StringBuilder payload = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            payload.append(line).append('\n');
        }
        return payload.toString();
    }

    public static String getEnvironmentInfo() {
        StringBuilder environmentInfo = new StringBuilder();

        // Retrieve environment variables
        environmentInfo.append("Server Name: ").append(System.getenv("COMPUTERNAME")).append("\n");
        environmentInfo.append("Java Version: ").append(System.getProperty("java.version")).append("\n");
        environmentInfo.append("OS: ").append(System.getProperty("os.name")).append("\n");

        // Retrieve system properties
        Enumeration<String> propertyNames = (Enumeration<String>) System.getProperties().propertyNames();
        while (propertyNames.hasMoreElements()) {
            String propertyName = propertyNames.nextElement();
            environmentInfo.append(propertyName).append(": ").append(System.getProperty(propertyName)).append("\n");
        }

        return environmentInfo.toString();
    }
}