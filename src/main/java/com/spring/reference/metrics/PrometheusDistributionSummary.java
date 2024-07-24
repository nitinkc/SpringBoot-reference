package com.spring.reference.metrics;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrometheusDistributionSummary {
    private final MeterRegistry meterRegistry;
    private DistributionSummary responseSizesSummary;

    @PostConstruct
    public void initializeMetrics() {
        // Distribution Summary (Summary)
        responseSizesSummary = DistributionSummary.builder("a_summary_http_response_sizes")
                .description("Distribution of HTTP response sizes")
                .baseUnit("bytes")
                .register(meterRegistry);

        processHttpResponse();
    }

    // Example method using Distribution Summary (Summary)
    public void processHttpResponse() {
        int simulateHttpResponseSize = (int) (Math.random() * 1000);
        // Simulate processing and get response size
        responseSizesSummary.record(simulateHttpResponseSize);
    }
}
