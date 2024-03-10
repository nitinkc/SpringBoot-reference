package com.spring.reference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.spring.reference.model")
@EnableJpaRepositories("com.spring.reference.dao") // Package containing repositories
@ComponentScan(basePackages = "com.spring.reference")
@EnableCaching
public class SpringBootReferenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReferenceApplication.class, args);
	}
}
