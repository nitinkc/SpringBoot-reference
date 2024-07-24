package com.spring.reference.schedulers;


import com.spring.reference.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DailyTaskScheduler {
    private final LicenseService licenseService;

    @Scheduled(cron = "0 0 0 * * *") // Executes at midnight every day
    //@Scheduled(fixedRate = 5000) // Executes every 5 seconds (5000 milliseconds) for testing
    public void runDailyTask() {
        licenseService.runJob();
    }
 }