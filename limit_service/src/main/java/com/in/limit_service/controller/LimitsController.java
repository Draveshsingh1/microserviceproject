package com.in.limit_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.limit_service.bean.Limit;
import com.in.limit_service.configuration.Configuration;

@RestController
public class LimitsController {
	@Autowired
	private Configuration configuration;
	@GetMapping("/limits")
	public Limit retriveLimit() {
		//return new Limit(1, 99);
		return new Limit(configuration.getMinimum(), configuration.getMaximum());
		
	}
	

}
