package com.spring.reference.config;

import redis.clients.jedis.Jedis;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component("redis-health")
@Slf4j
public class RedisHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try (Jedis jedis = new Jedis("localhost", 6379)) { // Update with your Redis host and port
            // Attempt to connect to Redis
            jedis.connect();
            String serverInfo = jedis.info("server");
            String version = serverInfo.split("\r\n")[1].split(":")[1];
            String os = getValueFromInfo(serverInfo, "os");
            String tcpPort = getValueFromInfo(serverInfo, "tcp_port");
            log.info("Ping redis-server on 6379 :: {}", jedis.ping());
            return Health.up()
                    .withDetail("status", "OK")
                    .withDetail("message", "Redis connection successful")
                    .withDetail("version", version)
                    .withDetail("os", os)
                    .withDetail("tcp_port", tcpPort)
                    .build(); // Redis connection successful
        } catch (Exception e) {
            return Health.down().withDetail("error", e.getMessage()).build(); // Redis connection failed
        }
    }

    private String getValueFromInfo(String info, String key) {
        String[] lines = info.split("\r\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2 && parts[0].equals(key)) {
                return parts[1];
            }
        }
        return null;
    }
}

