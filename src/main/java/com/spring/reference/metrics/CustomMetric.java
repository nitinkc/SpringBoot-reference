package com.spring.reference.metrics;

import com.spring.reference.service.LicenseService;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;


@Component
@AllArgsConstructor
public class CustomMetric {

    private final MeterRegistry meterRegistry;
    private final AtomicLong customValue = new AtomicLong(0);
    private final LicenseService licenseService;
    @PostConstruct
    public void initializeMetrics() {
        customValue.set(licenseService.runJob());
        // Untyped metric example
        meterRegistry.gauge("a_custom_untyped_metric", customValue); // Example of an untyped gauge
    }
}

