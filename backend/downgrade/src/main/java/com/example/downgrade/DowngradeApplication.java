package com.example.downgrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@PropertySource(value = "classpath:application-oauth.properties")
public class DowngradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DowngradeApplication.class, args);
	}

}
