package com.in.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircutBrakerController {
	private Logger logger=LoggerFactory.getLogger(CircutBrakerController.class);
	
	@GetMapping("/sampleAPI")
	//@Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
	@CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackMethod")
	public String SampleAPI() {
		logger.info("Sample Api Call Recived");
		ResponseEntity<String> data = new RestTemplate().getForEntity("http://localhost:8080/dummy", String.class);
		return data.getBody();
	}
	 public String fallbackMethod(Exception ex) {
		return "This is from fallback method";
		 
	 }
	 @GetMapping("/sampleAPI2")
	 @RateLimiter(name="sample-api2")
	 public String SampleAPI2() {
			return "Rate Limiter Applied";
		}

}
