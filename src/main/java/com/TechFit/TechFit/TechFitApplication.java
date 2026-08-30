package com.TechFit.TechFit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication

public class TechFitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechFitApplication.class, args);
	}

}
