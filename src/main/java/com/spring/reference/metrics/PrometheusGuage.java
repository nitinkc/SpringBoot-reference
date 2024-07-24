package com.spring.reference.metrics;

import com.spring.reference.service.LicenseService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrometheusGuage {
    private final MeterRegistry meterRegistry;
    AtomicInteger activeUsers = new AtomicInteger(0);
    private Gauge activeUsersGauge;

    Gauge daysRemainingGauge;
    AtomicInteger daysRemaining = new AtomicInteger(0);

    private final LicenseService licenseService;

    @PostConstruct
    public void initializeMetrics() {
    activeUsersGauge =
        Gauge.builder("a_guage_active_users", activeUsers, AtomicInteger::get)
            .description("Number of active users")
            .register(meterRegistry);

    // Define and register Gauge metric
        daysRemainingGauge =
                Gauge.builder("a_license_days_remaining", daysRemaining, AtomicInteger::get)
                        .description("Number of Days remaining until License expiration")
                        .register(meterRegistry);
        processMessage();
        // Schedule task to simulate user arrivals
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        //executorService.scheduleAtFixedRate(this::userLoggedIn, 0, 1, TimeUnit.SECONDS);
        userLoggedIn();
    }

    // Example method using Gauge
    public int userLoggedIn() {
        return activeUsers.incrementAndGet();
    }

    public void processMessage() {
        daysRemaining.set((int) licenseService.runJob());
    }
}
