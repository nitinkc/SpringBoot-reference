package com.spring.reference.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LicenseService {

    final String FILE_PATH = "src/main/resources/license/license.json";

    public long runJob() {
        long start = System.nanoTime();
        String expirationDateStr = "";
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONObject jsonObject = new JSONObject(content);
            expirationDateStr = jsonObject.getString("expirationDate");
        } catch (java.io.IOException e) {
            log.error("File not found or cannot be read: " + e.getMessage());
        } catch (org.json.JSONException e) {
            log.error("Error parsing JSON: " + e.getMessage());
        }
        log.info("License Expiration Date :: " + expirationDateStr);
        LocalDate expirationDate = LocalDate.parse(expirationDateStr, DateTimeFormatter.ISO_DATE);
        LocalDate currentDate = LocalDate.now();// Current date
        // Calculate the number of days between current date and expiration date
        long daysUntilExpiration = ChronoUnit.DAYS.between(currentDate, expirationDate);
        log.info("License Expiration - Total number of Days Remaining :: " + daysUntilExpiration);

        long endTimeMs = (System.nanoTime() - start) / 1000000;
        log.info("License Expiration Job took :: {}ms ", endTimeMs);
        return daysUntilExpiration;
    }
}