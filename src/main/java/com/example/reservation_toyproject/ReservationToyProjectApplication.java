package com.example.reservation_toyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan // configuration 스캔
@SpringBootApplication
public class ReservationToyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationToyProjectApplication.class, args);
	}

}
