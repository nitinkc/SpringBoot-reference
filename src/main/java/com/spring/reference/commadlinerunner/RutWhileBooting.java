package com.spring.reference.commadlinerunner;

import com.spring.reference.service.LicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(value = 1)
@ConditionalOnExpression("${dateExpiration:false}")
public class RutWhileBooting implements CommandLineRunner {

    private final LicenseService licenseService;
    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Runner : ExpiryDate");
        //Do processing
        licenseService.runJob();
    }
}