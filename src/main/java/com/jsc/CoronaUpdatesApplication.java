package com.jsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronaUpdatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaUpdatesApplication.class, args);
	}

}
