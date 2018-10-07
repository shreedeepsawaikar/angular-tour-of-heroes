package com.lolxz.tourofheroesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.lolxz")
@SpringBootApplication
public class TourOfHeroesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourOfHeroesServiceApplication.class, args);
	}
}
