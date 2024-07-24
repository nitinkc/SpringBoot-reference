package com.spring.reference.metrics;

import io.micrometer.core.instrument.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PrometheusTimer {

    private final MeterRegistry meterRegistry;
    private Timer requestTimer;
    private LongTaskTimer longTaskTimer;


    @PostConstruct
    public void initializeMetrics() {

        // Timer
        requestTimer = Timer.builder("a_timer_http_request_duration_seconds")
                .description("HTTP request duration")
                .register(meterRegistry);

        // Long Task Timer
        longTaskTimer = LongTaskTimer.builder("a_long_task_http_request_duration_seconds")
                .description("Duration of long-running tasks")
                .register(meterRegistry);

        // Schedule task to simulate user arrivals
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        //executorService.scheduleAtFixedRate(this::processHttpRequest, 0, 100, TimeUnit.SECONDS);

    }

    // Example method using Timer
    public void processHttpRequest() {
        Timer.Sample sample = Timer.start(meterRegistry);
        simulateRequestProcessing();
        sample.stop(requestTimer);
    }


    // Example method using Long Task Timer
    public void startLongTask() {
        LongTaskTimer.Sample sample = longTaskTimer.start();
        simulateLongTask();
        sample.stop();
    }

    // Simulate request processing
    private void simulateRequestProcessing() {
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Simulate long task
    private void simulateLongTask() {
        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
