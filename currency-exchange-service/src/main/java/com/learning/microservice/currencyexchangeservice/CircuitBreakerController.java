package com.learning.microservice.currencyexchangeservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;



@RestController
public class CircuitBreakerController {	
	
	@GetMapping("/pi")
	//@Retry(name = "sample-api" , fallbackMethod= "hardcodedResponse")
	@CircuitBreaker(name = "sample-api" , fallbackMethod= "hardcodedResponse")
	public String sampleApi() {
		System.out.println("Sample api received thrre times");
		ResponseEntity<String> forEntity= new RestTemplate().getForEntity("http://localhost:8080/some-dummy", String.class);
		
		return forEntity.getBody();
	}
	
	public String hardcodedResponse(Exception ex) {
		return "Nothing to worry about";
	}

}
