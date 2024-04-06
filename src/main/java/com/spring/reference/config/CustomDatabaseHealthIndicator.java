package com.spring.reference.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("postgres-health")
@Slf4j
public class CustomDatabaseHealthIndicator implements HealthIndicator {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Health health() {
        try {
            // Execute a test query to check if the database is reachable
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            // If the query executes successfully, the database is up
            return Health.up().build();
        } catch (Exception e) {
            // If an exception occurs, the database is not reachable
            return Health.down().withDetail("Error", "Database is not reachable").build();
        }
    }
}
