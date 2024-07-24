package com.spring.reference.metrics;

import com.spring.reference.service.LicenseService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PrometheusCounter {
    private final MeterRegistry meterRegistry;
    private Counter requestCounter;
    private final LicenseService licenseService;

  @PostConstruct
  public void initializeMetrics() {
    // Counter
    requestCounter = Counter.builder("a_counter_http_requests_total")
            .description("Number of times the job request run")
            .tags("licenseJobCounter", "example")
            .register(meterRegistry);

      // Schedule task to simulate user arrivals
      //Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::processRequest, 0, 100, TimeUnit.SECONDS);
  }

    // Example method using Counter
    public void processRequest() {
        licenseService.runJob();
        requestCounter.increment();
    }
}
