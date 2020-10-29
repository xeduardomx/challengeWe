package com.wenance.challenge;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.wenance.challenge.servicio.GetCriptoPriceService;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ChallengeWenanceApplication {
	@Autowired
	GetCriptoPriceService getCriptoPriceService;
	
	public static void main(String[] args) {
		SpringApplication.run(ChallengeWenanceApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		getCriptoPriceService.GetCriptoPrice();
	}
}
